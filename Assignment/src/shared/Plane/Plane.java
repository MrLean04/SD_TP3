package shared.Plane;

import entities.Pilot.States.*;
import interfaces.PlaneInterface;
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
public class Plane implements PilotP, PassengerP, HostessP, PlaneInterface {

    boolean readyFly = false;
    boolean lastF = false;
    private final Queue<Integer> inPlane = new LinkedList<>();
    private final Queue<Integer> temp = new LinkedList<>();
    private Airlift airlift;

    String rmiRegHostName;
    int rmiRegPortNumb;
    private final RepoInterface repoInterface;

    /**
     * Plane's Constructor
     * 
     */
    public Plane(RepoInterface repoInterface, String rmiRegHostName, int rmiRegPortNumb) {
        //
        // this.airlift=airlift;
        this.repoInterface = repoInterface;
        this.rmiRegHostName = rmiRegHostName;
        this.rmiRegPortNumb = rmiRegPortNumb;
    }

    // Pilot

    /**
     * Pilot's method. Pilot waits for all passengers to be in the plane so he can
     * be ready to fly.
     * 
     * @return a boolean representing if the wait is done
     */
    @Override
    public synchronized boolean WaitForAllInBoard() {
        setPilotState2Update(PilotState.WAIT_FOR_BOARDING);
        while (inPlane.size() < 5) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        readyFly = true;
        notifyAll();
        return true;
    }

    /**
     * Airlift's method. Update Repository
     */
    @Override
    public synchronized void upd() {
        setPilotState2Update(PilotState.FLYING_FORWARD);
    }

    /**
     * Pilot's method. Pilot sets some variables for his last flight.
     */
    @Override
    public synchronized void lFly() {
        // TO-DO
        inPlane.add(0); // empty seat
        inPlane.add(0); // empty seat
        inPlane.add(0); // empty seat
        inPlane.add(0); // empty seat
        lastF = true;
        notifyAll();
    }

    // Pilot
    public synchronized void FlyToDestinationPoint() {
        // TO-DO
    }

    // Pilot
    public synchronized void FlyToDeparturePoint() {
        // TO-DO
    }
    // Passenger

    /**
     * Passenger's method. Passenger boards the plane (enters the inPlane queue).
     * 
     * @param id id of the passenger
     * @return a boolean representing if the passenger is done boarding
     */
    @Override
    public synchronized boolean BoardThePlane(int id) {
        inPlane.add(id);
        setPassengerStatesUpdate(id, PassengerState.IN_FLIGHT);
        if (!lastF) {
            inPlaneUpdate(inPlane.size());
        } else {
            temp.add(0);
            inPlaneUpdate(temp.size());
        }
        System.out.println("inplane:" + inPlane);
        notifyAll();
        while (!readyFly) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        return true;

    }
    // Passenger

    /**
     * Passenger's method. Passenger waits to the end of flight.
     */
    public synchronized void WaitingForEndOfFlight() {
        // TO-DO
        try {
            wait((long) (1 + 200 * Math.random()));
        } catch (InterruptedException s) {
        }
        ;
    }

    /**
     * Pilot's method. Pilot clears the queues in the plane for the next flight.
     */
    @Override
    public synchronized void atDestinationPoint() {
        setPilotState2Update(PilotState.DEBOARDING);
        // TO-DO
        inPlane.clear();
        inPlaneUpdate(inPlane.size());
        // readyFly=false;
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPassengerStatesUpdate(int id, PassengerState state) {
		try {
            repoInterface.setPassengerStatesUpdate(id,state.toString());
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotState2Update(PilotState state) {
		try {
            repoInterface.setPilotState2Update(state.toString());
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotStateUpdate(int numberF, PilotState state) {
		try {
			repoInterface.setPilotStateUpdate(numberF, state.toString());
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setHostessStateUpdate(HostessState state) {
		try {
			repoInterface.setHostessStateUpdate(state.toString());
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inQueueUpdate(int size) {
		try {
			repoInterface.inQueueUpdate(size);
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inPlaneUpdate(int size) {
		try {
			repoInterface.inPlaneUpdate(size);
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void atDestinationUpdate(int size) {
		try {
			repoInterface.atDestinationUpdate(size);
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportBoarding() {
		try {
			repoInterface.reportBoarding();
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportCheck(int id) {
		try {
			repoInterface.reportCheck(id);
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportDeparted() {
		try {
			repoInterface.reportDeparted();
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportLDeparted() {
		try {
			repoInterface.reportLDeparted();
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportArrived() {
		try {
			repoInterface.reportArrived();
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }

    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportreturning() {
		try {
			repoInterface.reportreturning();
        }
        catch(RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + e.getMessage() + "!");
            System.exit(1);
        }
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
        String nameEntryObject = RegistryConfiguration.RMI_REGISTRY_PLANE_NAME;
        
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
            System.out.println("Plane registration exception: " + e.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Plane not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // Unexport; this will also remove us from the RMI runtime
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            System.exit(1);
        }

        System.out.println("Plane closed.");
    }
}
