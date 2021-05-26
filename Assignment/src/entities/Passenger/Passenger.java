package entities.Passenger;

import java.rmi.RemoteException;
import java.util.*;

import entities.Passenger.States.PassengerState;
import interfaces.DepartureInterface;
import interfaces.DestinationInterface;
import interfaces.PlaneInterface;


/**
 *
 * @author Leandro e João
 */
public class Passenger extends Thread {

	private PassengerState state;
	private int id;
	private boolean happyPassenger = false;
	private DepartureInterface departureInterface;
	private PlaneInterface planeInterface;
	private DestinationInterface destinationInterface;


	/**
	 * Passenger's constructor.
	 *
	 * @param id id of the passenger
	 */
	public Passenger(int id, DepartureInterface departureInterface, PlaneInterface planeInterface, DestinationInterface destinationInterface) {
		//to-do
		this.id=id;
		this.departureInterface = departureInterface;
		this.planeInterface=planeInterface;
		this.destinationInterface=destinationInterface;
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void travelToAirport() {
        try {
            departureInterface.travelToAirport();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void waitInQueue() {
        try {
            departureInterface.waitInQueue(this.id);
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean showDocuments() {
		boolean temp = false;
        try {
            temp = departureInterface.showDocuments(this.id);
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean waitinQueueFlight() {
		boolean temp = false;
        try {
            temp = departureInterface.waitinQueueFlight();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private boolean BoardThePlane() {
		boolean temp = false;
        try {
            temp = planeInterface.BoardThePlane(this.id);
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void Deboarding() {
        try {
            destinationInterface.Deboarding(this.id);
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private void WaitingForEndOfFlight() {
        try {
            planeInterface.WaitingForEndOfFlight();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void atAirport() {
        try {
            destinationInterface.atAirport(this.id);
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

	}

	@Override
	public void run() {
		this.setPassengerState(PassengerState.GOING_TO_AIRPORT);
		while (!this.happyPassenger) {
			switch (this.state) {
				case GOING_TO_AIRPORT:
					System.out.println("GOING_TO_AIRPORT " + id);
					travelToAirport();
					setPassengerState(PassengerState.IN_QUEUE);
					break;

				case IN_QUEUE:
					System.out.println("IN_QUEUE " + id);
					waitInQueue();
					if (showDocuments()) {
						if (waitinQueueFlight()) {
							setPassengerState(PassengerState.IN_FLIGHT);
						}
					}

					break;

				case IN_FLIGHT:
					System.out.println("IN_FLIGHT " + id);
					if (BoardThePlane()) {
						WaitingForEndOfFlight();
						Deboarding();
						setPassengerState(PassengerState.AT_DESTINATION);

					}
					break;

				case AT_DESTINATION:
					System.out.println("AT_DESTINATION " + id);
					//setPassengerState(PassengerState.GOING_TO_AIRPORT);
					atAirport();
					// Deboarding();
					this.happyPassenger = true;
					break;
			}
		}
	}

	/**
	 * Passenger's method. Change state of passenger and report status to log.
	 *
	 * @param state state of passenger
	 */
	private void setPassengerState(PassengerState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Passenger's method. Retrieves passenger's state.
	 *
	 * @return passenger's state
	 */
	private PassengerState getPassengersState() {
		return this.state;
	}

	/**
	 * Passenger's method. Retrieves passenger's id.
	 *
	 * @return passenger's id
	 */
	private int getPassengerId() {
		return this.id;
	}

}
