package shared.DestinationAirport;

import interfaces.Register;
import interfaces.DestinationInterface;
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
public class DestinationServer {
    /**
     * Server running the DestinationAirport.
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
        
        Destinationairport destination = new Destinationairport(repoInterface, rmiRegHostName, rmiRegPortNumb);
        DestinationInterface destinationInterface = null;
        
        try {
            destinationInterface = (DestinationInterface) UnicastRemoteObject.exportObject((Remote) destination, RegistryConfiguration.PORT_DESTINATION);
        }
        catch(RemoteException e) {
            System.out.println("Error generating Destination's stub: " + e.getMessage());
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
            reg.bind(RegistryConfiguration.RMI_REGISTRY_DESTINATION_NAME, destinationInterface);
        }
        catch(RemoteException e) {
            System.out.println("RepairArea registration exception: " + e.getMessage());
            System.exit(1);
        }
        catch(AlreadyBoundException e) {
            System.out.println("RepairArea already bound exception: " + e.getMessage());
            System.exit(1);
        }
        
    }
}
