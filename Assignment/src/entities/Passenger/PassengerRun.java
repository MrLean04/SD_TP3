package entities.Passenger;

import interfaces.DepartureInterface;
import interfaces.DestinationInterface;
import interfaces.PlaneInterface;
import interfaces.RepoInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import registry.RegistryConfiguration;

/**
 * Main customer class. Instantiates the customers.
 * 
 * @author Leandro e João
 */
public class PassengerRun {

    /**
     * Main customer class. Instantiates the customers.
     * 
     * @param args -
     */
    public static void main(String[] args) {

        // Initialize RMI Configurations
        String rmiRegHostName = args[0];
        int rmiRegPortNumb = Integer.parseInt(args[1]);

        /*
         * String rmiRegHostName = "localhost"; int rmiRegPortNumb = 1099;
         */
        Registry registry = null;

        // Initialize RMI Invocations
        RepoInterface repositoryInterface = null;
        DepartureInterface departureInterface = null;
        PlaneInterface planeInterface=null;
        DestinationInterface destinationInterface=null;
        // localização por nome do objecto remoto no serviço de registos RMI

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.out.println("RMI registry creation exception: " + e.getMessage());
            System.exit(1);
        }

        // localização por nome do objecto remoto no serviço de registos RMI
        try {
            repositoryInterface = (RepoInterface) registry.lookup(RegistryConfiguration.RMI_REGISTRY_REPO_NAME);
        } catch (RemoteException e) {
            System.out.println("Excepção na localização do Repository: " + e.getMessage() + "!");
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("O Repository não está registada: " + e.getMessage() + "!");
            System.exit(1);
        }

        try {
            departureInterface = (DepartureInterface) registry
                    .lookup(RegistryConfiguration.RMI_REGISTRY_DEPARTURE_NAME);

        } catch (RemoteException e) {
            System.out.println("Excepção na localização do Departure: " + e.getMessage() + "!");
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("O Departure não está registada: " + e.getMessage() + "!");
            System.exit(1);
        }

        try {
            planeInterface = (PlaneInterface) registry
                    .lookup(RegistryConfiguration.RMI_REGISTRY_PLANE_NAME);

        } catch (RemoteException e) {
            System.out.println("Excepção na localização do Plane: " + e.getMessage() + "!");
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("O Plane não está registada: " + e.getMessage() + "!");
            System.exit(1);
        }

        try {
            destinationInterface = (DestinationInterface) registry
                    .lookup(RegistryConfiguration.RMI_REGISTRY_DESTINATION_NAME);

        } catch (RemoteException e) {
            System.out.println("Excepção na localização do Destination: " + e.getMessage() + "!");
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("O Destination não está registada: " + e.getMessage() + "!");
            System.exit(1);
        }

        Passenger[] passengers = new Passenger[21];

        for (int i = 0; i < 21; i++) {
            passengers[i] = new Passenger(i, departureInterface, planeInterface,destinationInterface);
            System.out.println("Passenger " + i + " has started");
            passengers[i].start();
        }

        for (int i = 0; i < 21; i++) {
            try {
                passengers[i].join();
                System.out.println("Passenger " + i + " has died");
            } catch (InterruptedException e) {
                System.out.println("Passenger " + i + " has died - exeption");
            }
        }

        try {
            repositoryInterface.finished();
        } catch (RemoteException ex) {
            System.out.println("Error closing all!");
            System.exit(1);
        }
        
        System.out.println("Passenger Done!");

    }
}