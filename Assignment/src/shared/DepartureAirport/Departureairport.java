package shared.DepartureAirport;

import entities.Pilot.States.*;
import interfaces.DepartureInterface;
import interfaces.Register;
import interfaces.RepoInterface;
import registry.RegistryConfiguration;
import shared.Repo.*;
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
public class Departureairport implements PilotDA, PassengerDA, HostessDA, DepartureInterface {

	private final int max = 10;
	private final int min = 5;
	int nPassengerPlane = 0;
	private int nextPassenger = -1;
	private boolean pilotInpark = false;
	private boolean pilotready = false;
	private boolean inaArraivalAir = false;
	private boolean readyfly = false;
	private boolean readyForCheckDoc = false;
	private boolean PilotReadyFly = false;
	private boolean PassengerReadyBoarding = false;
	private final HashMap<Integer, Boolean> documentCheck = new HashMap<>();
	private final Queue<Integer> inQueue = new LinkedList<>();
	private final Queue<Integer> inPlane = new LinkedList<>();
	private final Queue<Integer> inqueueforboarding = new LinkedList<>();
	private final Queue<Integer> documentsQueue = new LinkedList<>();
	private int numberF = 0;
	private boolean lastF = false;
	private Airlift airlift;

	String rmiRegHostName;
    int rmiRegPortNumb;
    private final RepoInterface repoInterface;

	/**
	 * Departureairport's Constructor
	 */
	public Departureairport(RepoInterface repoInterface, String rmiRegHostName, int rmiRegPortNumb) {
		//
		this.repoInterface = repoInterface;
        this.rmiRegHostName = rmiRegHostName;
        this.rmiRegPortNumb = rmiRegPortNumb;
	}

	// Pilot

	/**
	 * Pilot's method. Pilot arrives at transfer gate
	 * 
	 */
	@Override
	public synchronized void parkAtTransfer() {
		pilotInpark = true;
		numberF++;
		//setPilotStateUpdate(numberF, PilotState.AT_TRANSFER_GATE);
		notifyAll();
	}

	/**
	 * Pilot's method. Pilot is ready for boarding
	 * 
	 */
	@Override
	public synchronized void readyForBoarding() {
		pilotready = true;
		//setPilotStateUpdate(numberF, PilotState.READY_FOR_BOARDING);
		//reportBoarding();
		// pilotInpark = false;
	}

	/**
	 * Pilot's method. Pilot waits for all passengers to board.
	 * 
	 */
	@Override
	public synchronized void WaitForBoarding() {
		//setPilotStateUpdate(numberF, PilotState.WAIT_FOR_BOARDING);
		while (!PilotReadyFly) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		// pilotInpark=false;
	}

	/**
	 * Pilot's method. Pilot sets the last flight flag to true.
	 */
	@Override
	public synchronized void last() {
		lastF = true;
	}

	// Hostess

	/**
	 * Hostess's method. Hostess checks if pilot is at the transfer gate and sets
	 * the checking documents flag to true.
	 */
	@Override
	public synchronized void preparePassBoarding() {
		if (pilotInpark) {
			readyForCheckDoc = true;
		}

	}

	/**
	 * Hostess's method. Retrieves if the airport queue is not empty.
	 * 
	 * @return a boolean representing if the queue is not empty
	 */
	@Override
	public synchronized boolean queueNotEmpty() {
		// setHostessStateUpdate();
		if (inQueue.size() != 0)
			return true;
		return false;
	}

	/**
	 * Hostess's method. Hostess checks the documents of a passenger.
	 * 
	 * @return a boolean representing if a passenger was checked
	 */
	@Override
	public synchronized boolean checkAndWait() {
		// TO-DO

		while (nextPassenger != -1) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (inQueue.size() == 0 || !readyForCheckDoc) {
			readyForCheckDoc = false;
			return false;
		}
		System.out.println(inQueue.toString());
		nextPassenger = inQueue.poll();
		//reportCheck(nextPassenger);
		//inQueueUpdate(inQueue.size());
		// System.out.println(nextPassenger + " entrei na queue");
		documentCheck.put(nextPassenger, true);
		inPlane.add(nextPassenger);
		//setHostessStateUpdate(HostessState.CHECK_PASSENGER);
		nPassengerPlane++;
		notifyAll();
		System.out.println(documentCheck);
		return true;

	}

	/**
	 * Hostess's method. Hostess checks if the plane is ready to fly and if so
	 * updates some variables.
	 * 
	 * @return a boolean representing if the plane is ready to fly
	 */
	@Override
	public synchronized boolean planeReadyToTakeoff() {
		if ((inPlane.size() >= 5 && inPlane.size() <= 10) || lastF) {
			readyForCheckDoc = false;
			PilotReadyFly = true;
			PassengerReadyBoarding = true;
			pilotInpark = false;
			inPlane.clear();
			System.out.println(numberF);
			if (!lastF) {
				//reportDeparted();
			} else {
				//reportLDeparted();
			}

			notifyAll();
			return true;
		}
		return false;
	}

	/**
	 * Pilot's method. Hostess stops passengers from boarding the plane
	 * 
	 */
	@Override
	public synchronized void everyoneStops() {
		PilotReadyFly = false;
		PassengerReadyBoarding = false;
	}

	/**
	 * Hostess's method. Hostess waits for the plane to come back from its flight.
	 */
	@Override
	public synchronized void waitForNextFlightH() {
		// TO-DO
		//setHostessStateUpdate(HostessState.WAIT_FOR_NEXT_FLIGHT);
		notifyAll();
		while (!pilotInpark) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}

	}

	/**
	 * Hostess's method. Hostess checks if there's more passengers to check.
	 * 
	 * @return a boolean representing if the hostess job is done
	 */
	@Override
	public synchronized boolean hostessJobDone() {
		//setHostessStateUpdate(HostessState.READY_TO_FLY);
		if (inQueue.size() == 0) {
			return true;

		}
		return false;
	}

	// Passenger

	/**
	 * Passenger's method. Passenger travel to airport.
	 * 
	 */
	@Override
	public synchronized void travelToAirport() {
		// TO-DO
		try {
			wait((long) (1 + 200 * Math.random()));
		} catch (InterruptedException s) {
		}
		;
	}

	/**
	 * Passenger's method. Passenger waits until it's his time to board the plane.
	 * 
	 * @return a boolean representing if the passenger can board the plane
	 */
	@Override
	public synchronized boolean waitinQueueFlight() {
		// TO-DO

		while (!PassengerReadyBoarding) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}

		return true;
	}

	/**
	 * Passenger's method. Passenger enters the airport queue and waits until it's
	 * his turn.
	 * 
	 * @param id id of the passenger
	 */
	@Override
	public synchronized void waitInQueue(int id) {
		inQueue.add(id);
		//setPassengerStatesUpdate(id, PassengerState.IN_QUEUE);
		//inQueueUpdate(inQueue.size());
		// documentCheck.put(id, false);
		// System.out.println(id+ "");
		// notifyAll();
		while (nextPassenger != id) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		nextPassenger = -1;
		notifyAll();
	}

	/**
	 * Passenger's method. Checks if the passenger has been moved to the
	 * documentsQueue.
	 * 
	 * @param id id of the passenger
	 * @return a boolean representing if the passenger exists in the documentsQueue
	 */
	@Override
	public synchronized boolean showDocuments(int id) {
		// TO-DO
		return documentCheck.get(id) != null;
	}

	/**
	 * Passenger's method. Unused
	 * 
	 * @param id id of the passenger
	 * 
	 */
	@Override
	public synchronized void waitForNextFlightP(int id) {
		// TO-DO
		if (inQueue.size() != 0) {
			notifyAll();
			while (!pilotInpark && !showDocuments(id)) {
				try {
					wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}

	/**
	 * Number of the flight.
	 * 
	 * @return a integer representing the number of the current flight
	 */
	public synchronized int getNumberF() {
		return numberF;
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
        String nameEntryObject = RegistryConfiguration.RMI_REGISTRY_DEPARTURE_NAME;
        
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
            System.out.println("Departure registration exception: " + e.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Departure not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // Unexport; this will also remove us from the RMI runtime
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            System.exit(1);
        }

        System.out.println("Departure closed.");
    }

}
