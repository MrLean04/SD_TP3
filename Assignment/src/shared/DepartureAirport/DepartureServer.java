package shared.DepartureAirport;

import interfaces.Register;
import interfaces.DepartureInterface;
import interfaces.RepoInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import registry.RegistryConfiguration;
//import shared.DepartureAirport.*;
/**
 * 
 * @author Leandro e João
 */

public class DepartureServer {
    /**
     * Server running the DepartureAirport.
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
        
        Departureairport departure = new Departureairport(repoInterface, rmiRegHostName, rmiRegPortNumb);
        DepartureInterface departureInterface = null;
        
        try {
            departureInterface = (DepartureInterface) UnicastRemoteObject.exportObject((Remote) departure, RegistryConfiguration.PORT_DEPARTURE);
        }
        catch(RemoteException e) {
            System.out.println("Error generating Departure's stub: " + e.getMessage());
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
            reg.bind(RegistryConfiguration.RMI_REGISTRY_DEPARTURE_NAME, departureInterface);
        }
        catch(RemoteException e) {
            System.out.println("Departure registration exception: " + e.getMessage());
            System.exit(1);
        }
        catch(AlreadyBoundException e) {
            System.out.println("Departure already bound exception: " + e.getMessage());
            System.exit(1);
        }
        
    }
}
