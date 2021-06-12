package shared.Repo;

import java.util.Queue;

import entities.Hostess.States.*;
import entities.Passenger.States.*;
import entities.Pilot.States.*;
import interfaces.DepartureInterface;
import interfaces.DestinationInterface;
import interfaces.PlaneInterface;
import interfaces.Register;
import interfaces.RepoInterface;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import registry.RegistryConfiguration;

//import genclass.FileOp;
//import genclass.GenericIO;
//import genclass.TextFile;


/**
 *
 * @author Leandro e Jo�o
 */
public class Airlift implements RepoInterface{

    private final int nPassengers = 21;
    private String[] PassengerStates;	
    private String PilotState;
    private String HostessState;
    private int inqueue=0;
    private int inPlane=0;
    private int atDestination=0;
    private int numberF=1;
    private final String fileName = "Airlift.txt";

    private int n_entities = 3;

    private final int rmiRegPortNumb;
    private final String rmiRegHostName;
    //TextFile log = new TextFile();
    /**
     * Airlift's Constructor
     * 
     */

    public Airlift(String rmiRegHostName, int rmiRegPortNumb){
      //this.nPassengers =21;
      //this.fileName = "Airlift.log";
      //if(FileOp.exists(".", fileName)) {
        //FileOp.deleteFile(".", fileName);
      //}
      PassengerStates = new String[nPassengers];
    
        for (int i = 0; i < nPassengers; i++) {
			PassengerStates[i] = "GTA";
		}
		PilotState = "ATG";
    HostessState = "WFNF";
      //this.nPassengers =21;
      //this.fileName = "Airlift.log";
      //if ((fileName != null) && !("".equals(fileName))) {
        //this.fileName = fileName;

        this.rmiRegHostName = rmiRegHostName;
        this.rmiRegPortNumb = rmiRegPortNumb;
        //reportInitialStatus();
    //}
      //

      
    }
    /**
     * Airlift's method. Update from departure airport.
     * @param id id of the passenger
     * @param state current state of the passenger
     */
    public synchronized void setPassengerStatesUpdate(int id, String state){
      this.PassengerStates[id]=  state; 
      //reportStatus();
    }
    
    /**
     * Airlift's method. Update from departure airport.
     * @param size size of queue from departure airport
     */
    public synchronized void inQueueUpdate( int size){
      this.inqueue= size;
    }
    /**
     * Airlift's method. Update from departure airport.
     * @param state current state of the hostess
     */
    public synchronized void setHostessStateUpdate(String state){
      this.HostessState= state; 
      //reportStatus();
    }
    
    /**
     * Airlift's method. Update from departure airport.
     * @param numberF number of the flight
     * @param state current state of the pilot
     */
    public synchronized void setPilotStateUpdate( int numberF, String state){
      this.numberF= numberF;
      this.PilotState= state;  
      //reportStatus();
    }
    /**
     * Airlift's method. Update from plane.
     * @param state current state of the pilot
     */
    public synchronized void setPilotState2Update(String state) {
      this.PilotState= state; 
      //reportStatus();
    }
    /**
     * Airlift's method. Update from plane.
     * @param size size of  queue from plane 
     */
    public synchronized void inPlaneUpdate(int size) {
      this.inPlane= size;
    }
    
    /**
     * Airlift's method. Update from plane.
     * @param size size of Destination from DestinationAirport 
     */
    public synchronized void atDestinationUpdate(int size) {
      this.atDestination=size;
    }
    
    /**
     *First log report.
     */
    
    public void reportInitialStatus() {
        //TextFile log = new TextFile(); 

        //fileName = "Airlift.log";
        /*if (!log.openForWriting(".", fileName)) {
            GenericIO.writelnString("A opera�ao de cria�ao do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
        log.writelnString("                                       Airlift - Description of the internal state");
        
        log.writelnString("");
		
        log.writeString(" PT   HT   P00  P01  P02  P03  P04  P05  P06  P07  P08  P09  P10  P11  P12  P13  P14  P15  P16  P17  P18  P19  P20         InQ InF PTAL\n");

        log.writeString( PilotState.toString() + "  " + HostessState.toString() + "  ");

        for (int i = 0; i < 21; i++) {
          log.writeString(PassengerStates[i].toString() + "  ");
        }
        log.writeString("   " + inqueue + "   " +  inPlane + "   " + atDestination + "\n");	

        if (!log.close()) {
            GenericIO.writelnString("A opera�ao de fecho do ficheiro " + fileName + " falhou! -initialstatus");
            System.exit(1);
        }
        reportStatus();*/
    }
    /**
     *Report status to log
     */
private void reportStatus() {
        
	/*TextFile log = new TextFile ();                      // instantiation of a text file handler

    String lineStatus = "";                              // state line to be printed

    if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of opening for appending the file " + fileName + " failed!");
        System.exit (1);
      }
    switch (PilotState)
      { case "ATG":   lineStatus += "ATG  ";
                                      break;
        case "RFB": lineStatus += "RFB  ";
                                      break;
        case "WFB": lineStatus += "WFB  ";
                                      break;
        case "FF": lineStatus += "FF  ";
                                      break;
        case "D": lineStatus += "D  ";
                                      break;
        case "FB": lineStatus += "FB  ";
                                      break;
      }
    
    switch (HostessState)
      { case "WFNF" :   lineStatus += "WFNF  ";
                                      break;
        case "WFP":   lineStatus += "WFP  ";
                                      break;
        case "CP":   lineStatus += "CP  ";
                                      break;
        case "RTF":   lineStatus += "RTF  ";
                                      break;
      }
    
    for (int i = 0; i < 21; i++)
      switch (PassengerStates[i])
      { case "GTA":   lineStatus += " GTA ";
                                      break;
        case "IQ":   lineStatus += " IQ  ";
                                      break;
        case "IF":   lineStatus += "IF  ";
                                      break;
        case "AD":   lineStatus += "AD  ";
                                      break;
      }
    
    lineStatus += "    " + inqueue + "     " + inPlane + "      " + atDestination;
    log.writelnString (lineStatus);
    if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
    }
/**
*  Report that the flight has departed.
*  Internal operation.
*/


public void reportBoarding()
{
  /* TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString (" Flight " + numberF + ": boarding started.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

/**
*  Report that the flight has departed. Internal operation.
*  @param id 
*/


public void reportCheck(int id)
{
   /*TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": passenger " + id +  " checked.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

  /**
*  Report that the flight has departed.
*  Internal operation.
*/


public void reportDeparted()
{
   /*TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": departed with " + "5" +  " passengers.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

/**
*  Report that the flight has departed.
*  Internal operation.
*/


public void reportLDeparted()
{
   /*TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": departed with " + "1" +  " passengers.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

/**
*  Report that the flight has arrived at arrival airport.
*  Internal operation.
*/

public void reportArrived()
{/*
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": arrived.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

  /**
*  Report that the flight is returning to the initial airport.
*  Internal operation.
*/

public void reportreturning()
{
   /*TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending(".", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": returning.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }*/
}

@Override
    public void finished() throws RemoteException {
        n_entities--;
        if (n_entities > 0) {
            return;
        }
        terminateServers();
    }
    
    private void terminateServers() { 
        Register reg = null;
        Registry registry = null;
        
        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException ex) {
            System.out.println("Erro na localização do registo");
            System.exit(1);
        }
        
        String nameEntryBase = RegistryConfiguration.RMI_REGISTER_NAME;
        String nameEntryObject = RegistryConfiguration.RMI_REGISTRY_REPO_NAME;
        
        
        try {
            DepartureInterface di = (DepartureInterface) registry.lookup(RegistryConfiguration.RMI_REGISTRY_DEPARTURE_NAME);
            di.signalShutdown();
        } 
        catch (RemoteException e) {
            System.out.println("Exception thrown while locating Departure: " + e.getMessage() + "!");
            System.exit(1);
        }
        catch (NotBoundException e) {
            System.out.println("Departure is not registered: " + e.getMessage() + "!");
            System.exit(1);
        }
        
        try {
            PlaneInterface pi = (PlaneInterface) registry.lookup(RegistryConfiguration.RMI_REGISTRY_PLANE_NAME);
            pi.signalShutdown();
        } 
        catch (RemoteException e) {
            System.out.println("Exception thrown while locating Plane: " + e.getMessage() + "!");
            System.exit(1);
        }
        catch (NotBoundException e) {
            System.out.println("Plane is not registered: " + e.getMessage() + "!");
            System.exit(1);
        }
        
        try {
            DestinationInterface dei = (DestinationInterface) registry.lookup(RegistryConfiguration.RMI_REGISTRY_DESTINATION_NAME);
            dei.signalShutdown();
        } 
        catch (RemoteException e) {
            System.out.println("Exception thrown while locating Destination: " + e.getMessage() + "!");
            System.exit(1);
        }
        catch (NotBoundException e) {
            System.out.println("Destination is not registered: " + e.getMessage() + "!");
            System.exit(1);
        }
        try {
            reg = (Register) registry.lookup(nameEntryBase);
        } 
        catch (RemoteException e) {
            System.out.println("RegisterRemoteObject lookup exception: " + e.getMessage());
            System.exit(1);
        } 
        catch (NotBoundException e) {
            System.out.println("RegisterRemoteObject not bound exception: " + e.getMessage());
            System.exit(1);
        }
        try {
            reg.unbind(nameEntryObject);
        } catch (RemoteException e) {
            System.out.println("Log registration exception: " + e.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Log not bound exception: " + e.getMessage());
            System.exit(1);
        }
        
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } 
        catch (NoSuchObjectException ex) {
            System.exit(1);
        }
        System.out.println("Repository closed.");      
    }

}
