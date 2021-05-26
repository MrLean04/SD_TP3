package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Leandro e Jo�o
 */
public interface DestinationInterface extends Remote{

    public boolean AnnounceArrival() throws RemoteException;
    
    public  boolean goBack() throws RemoteException;
    
    public boolean lastF() throws RemoteException;

    public  void atAirport(int id) throws RemoteException;

    public void Deboarding(int id) throws RemoteException;

    public void zeroCount() throws RemoteException;
    
    public void signalShutdown() throws RemoteException;
    
}
