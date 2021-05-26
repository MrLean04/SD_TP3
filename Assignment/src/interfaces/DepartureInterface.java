package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Leandro e Jo�o
 */
public interface DepartureInterface extends Remote {

    public void parkAtTransfer() throws RemoteException;

    public void readyForBoarding() throws RemoteException;
    
    public void WaitForBoarding() throws RemoteException;

    public void last() throws RemoteException;

    public  void preparePassBoarding() throws RemoteException;

    public  boolean queueNotEmpty() throws RemoteException;

    public  boolean checkAndWait() throws RemoteException;

    public  boolean planeReadyToTakeoff() throws RemoteException;

    public  void everyoneStops() throws RemoteException;

    public  void waitForNextFlightH() throws RemoteException;

    public  boolean hostessJobDone() throws RemoteException;

    public  void travelToAirport() throws RemoteException;

    public  boolean waitinQueueFlight() throws RemoteException;

    public  void waitInQueue(int id) throws RemoteException;

    public  boolean showDocuments(int id) throws RemoteException;

    public  void waitForNextFlightP(int id) throws RemoteException;

    public int getNumberF() throws RemoteException;

    public void signalShutdown() throws RemoteException;
    
}


