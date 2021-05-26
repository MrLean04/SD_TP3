package shared.DestinationAirport;


import entities.Pilot.States.*;
import interfaces.DestinationInterface;
import interfaces.Register;
import interfaces.RepoInterface;
import registry.RegistryConfiguration;
import shared.Repo.Airlift;
import entities.Passenger.States.*;
import entities.Hostess.States.*;
import entities.Pilot.Interfaces.*;
import entities.Passenger.Interfaces.*;
import entities.Hostess.Interfaces.*;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 *
 * @author Leandro and Jo�o
 */
public class Destinationairport implements PilotDSA, PassengerDSA, HostessDSA , DestinationInterface {


    private final Queue<Integer> inDestinationAirport = new LinkedList<>();
    private final Queue<Integer> inDebording = new LinkedList<>();
    private boolean l = false;
    private int count = 0;
    private Airlift airlift;

    String rmiRegHostName;
    int rmiRegPortNumb;
    private final RepoInterface repoInterface;

    /**
     * Destinationairport's Constructor
     * 
     */
    public Destinationairport(RepoInterface repoInterface, String rmiRegHostName, int rmiRegPortNumb) {//
        // this.airlift=airlift;
        this.repoInterface = repoInterface;
        this.rmiRegHostName = rmiRegHostName;
        this.rmiRegPortNumb = rmiRegPortNumb;
    }
    // Pilot

    /**
     * Pilot's method. When all passengers have been transported to the destination
     * airport the pilot will announce his final arrival.
     * 
     * @return a boolean representing if all passengers have been transported
     */
    @Override
    public synchronized boolean AnnounceArrival() {
        // TO-DO
        setPilotState2Update(PilotState.FLYING_BACK);
        reportArrived();
        if (!l) {
            System.out.println("contador:" + " " + count);
            while (count != 5) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            // System.out.println("aqui2");
            return false;
        }
        if (inDestinationAirport.size() == 21)
            return true;
        return false;
    }

    /**
     * Pilot's method. Pilot will wait for all the passengers to get on the
     * destination airport so he can fly back.
     * 
     * @return a boolean representing if it's time to fly back
     */
    @Override
    public synchronized boolean goBack() {
        // TO-DO
        if (inDebording.size() == 0) {
            reportreturning();
            return true;
        }
        return false;
    }

    /**
     * Pilot's method. Pilot checks if it's the last flight. return a boolean
     * representing if it the next flight is the last.
     */
    @Override
    public synchronized boolean lastF() {
        // TO-DO
        if (inDestinationAirport.size() == 20) {
            l = true;
            notifyAll();
            return true;
        }
        return false;
    }

    /**
     * Pilot's method. Pilot adds a passengers to the destination airport queues.
     * 
     * @param id id of the passenger
     */
    @Override
    public synchronized void atAirport(int id) {
        // TO-DO
        inDestinationAirport.add(id);
        setPassengerStatesUpdate(id, PassengerState.AT_DESTINATION);
        atDestinationUpdate(inDestinationAirport.size());
        inDebording.remove(id);

    }

    // Passenger

    /**
     * Passenger's method. Passenger removes himself from the inDeboarding queue.
     * 
     * @param id id of the passenger
     */
    @Override
    public synchronized void Deboarding(int id) {
        // TO-DO
        inDebording.add(id);
        count++;
        // System.out.println(" mais um " +count);
        notifyAll();
    }

    /**
     * Pilot's method. Used to put the variable with the value null.
     */
    public synchronized void zeroCount() {
        count = 0;
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPassengerStatesUpdate(int id, PassengerState state) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotState2Update(PilotState state) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotStateUpdate(int numberF, PilotState state) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setHostessStateUpdate(HostessState state) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inQueueUpdate(int size) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inPlaneUpdate(int size) {
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void atDestinationUpdate(int size) {
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportBoarding() {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportCheck(int id) {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportDeparted() {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportLDeparted() {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportArrived() {

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportreturning() {
    }

    @Override
    public void signalShutdown() {
        Register reg = null;
        Registry registry = null;
        
        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } 
        catch (RemoteException ex) {
            System.out.println("Erro ao localizar o registo");
            System.exit(1);
        }
        
        String nameEntryBase = RegistryConfiguration.RMI_REGISTER_NAME;
        String nameEntryObject = RegistryConfiguration.RMI_REGISTRY_DESTINATION_NAME;
        
        try {
            reg = (Register) registry.lookup(nameEntryBase);
        } catch (RemoteException e) {
            System.out.println("RegisterRemoteObject lookup exception: " + e.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("RegisterRemoteObject not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        // Unregister ourself
        try {
            reg.unbind(nameEntryObject);
        } catch (RemoteException e) {
            System.out.println("Destination registration exception: " + e.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Destination not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // Unexport; this will also remove us from the RMI runtime
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            System.exit(1);
        }

        System.out.println("Destination closed.");
    }
}
