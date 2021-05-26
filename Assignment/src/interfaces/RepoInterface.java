package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Leandro e Jo�o
 */
public interface RepoInterface extends Remote{

    public void setPassengerStatesUpdate(int id, String state) throws RemoteException;

    public  void inQueueUpdate( int size) throws RemoteException;

    public void setHostessStateUpdate(String state) throws RemoteException;

    public void setPilotState2Update(String state)  throws RemoteException;

    public void inPlaneUpdate(int size)  throws RemoteException;

    public void atDestinationUpdate(int size)  throws RemoteException;

    public void reportInitialStatus()  throws RemoteException;

    public void reportBoarding() throws RemoteException;

    public void reportCheck(int id) throws RemoteException;

    public void reportDeparted() throws RemoteException;

    public void reportLDeparted() throws RemoteException;

    public void reportArrived() throws RemoteException;

    public void reportreturning() throws RemoteException;
    
    public void finished() throws RemoteException;
    
}
