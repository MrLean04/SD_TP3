package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Leandro e Jo�o
 */
public interface PlaneInterface extends Remote{

    public boolean WaitForAllInBoard() throws RemoteException;

    public  void upd() throws RemoteException;

    public  void lFly() throws RemoteException;

    public boolean BoardThePlane(int id) throws RemoteException;

    public void WaitingForEndOfFlight() throws RemoteException;

    public  void atDestinationPoint() throws RemoteException;

    public void signalShutdown() throws RemoteException;
    
}
