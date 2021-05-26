package entities.Passenger;

//import java.lang.ref.Cleaner;
import java.util.*;

import entities.Passenger.States.PassengerState;


/**
 *
 * @author Leandro e João
 */
public class Passenger extends Thread {

	private PassengerState state;
	private final int id=0;
	private boolean happyPassenger = false;


	/**
	 * Passenger's constructor.
	 *
	 * @param id id of the passenger
	 */
	public Passenger(int id) {
		//to-do
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void travelToAirport() {
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void waitInQueue() {

	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean showDocuments() {
		return false;
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean waitinQueueFlight() {
		return false;
	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private boolean BoardThePlane() {
		return false;
	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void Deboarding() {

	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private void WaitingForEndOfFlight() {

	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void atAirport() {

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
