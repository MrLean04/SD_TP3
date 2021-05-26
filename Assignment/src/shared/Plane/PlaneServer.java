package shared.Plane;

import interfaces.PlaneInterface;
import interfaces.Register;
import interfaces.RepoInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import registry.RegistryConfiguration;

/**
 * 
 * @author Leandro e João
 */
public class PlaneServer {
    /**
     * Server running the Plane.
     * @param args -
     */    
    public static void main(String[] args) {
    
        String rmiRegHostName = args[0];
        int rmiRegPortNumb = Integer.parseInt(args[1]);
        
        RepoInterface repoInterface = null;
    
        try {
            Registry registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
            repoInterface = (RepoInterface) registry.lookup(RegistryConfiguration.RMI_REGISTRY_REPO_NAME);
        }
        catch (RemoteException e) {
            System.out.println("Error getting repository's location: " + e.getMessage());
            System.exit(1);
        }
        catch(NotBoundException e) {
            System.out.println("Repository isn't registred: " + e.getMessage());
            System.exit(1);
        }
        
        Plane plane = new Plane(repoInterface, rmiRegHostName, rmiRegPortNumb);
        PlaneInterface planeInterface = null;
        
        try {
            planeInterface = (PlaneInterface) UnicastRemoteObject.exportObject((Remote) plane, RegistryConfiguration.PORT_PLANE);
        }
        catch(RemoteException e) {
            System.out.println("Error generating Plane's stub: " + e.getMessage());
            System.exit(1);
        }
        
        Registry registry = null;
        Register reg = null;
        
        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        }
        catch(RemoteException e) {
            System.out.println("Error while creating RMI registry: " + e.getMessage());
            System.exit(1);
        }
        
        try {
            reg = (Register) registry.lookup(RegistryConfiguration.RMI_REGISTER_NAME);
        }
        catch(RemoteException e) {
            System.out.println("RegisterRemoteObject lookup exception: " + e.getMessage());
            System.exit(1);
        }
        catch(NotBoundException e) {
            System.out.println("RegisterRemoteObject not bound exception: " + e.getMessage());
            System.exit(1);
        }
        
        try {
            reg.bind(RegistryConfiguration.RMI_REGISTRY_PLANE_NAME, planeInterface);
        }
        catch(RemoteException e) {
            System.out.println("Plane registration exception: " + e.getMessage());
            System.exit(1);
        }
        catch(AlreadyBoundException e) {
            System.out.println("Plane already bound exception: " + e.getMessage());
            System.exit(1);
        }
        
    }
    
}
