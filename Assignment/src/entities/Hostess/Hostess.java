package entities.Hostess;

//import java.lang.ref.Cleaner;
import java.util.*;

import entities.Hostess.States.HostessState;

/**
 *
 * @author Leandro e Jo�o
 */
public class Hostess extends Thread {

	private HostessState state;
	boolean happyhostess = false;

	/**
	 * Hostess's constructor.
	 *
	 */
	public Hostess() {
		//
	}

	/**
	 * Hostess's method. Send Message to Departure Interface
	 *
	 */
	private void preparePassBoarding() {
	}

	/**
	 * Hostess's method. Send Message to Departure Interface
	 *
	 */
	private boolean checkAndWait() {
		return false;
	}

	/**
	 * Hostess's method. Send Message to Departure Interface
	 *
	 */
	private boolean planeReadyToTakeoff() {
		return false;
	}

	/**
	 * Hostess's method. Send Message to Departure Interface
	 *
	 */
	private boolean hostessJobDone() {
		return false;
	}

	/**
	 * Hostess's method. Send Message to Departure Interface
	 *
	 */
	private void waitForNextFlightH() {

	}

	@Override
	public void run() {
		this.setHostessState(HostessState.WAIT_FOR_PASSENGER);
		while (!happyhostess) {
			switch (this.state) {
				case WAIT_FOR_PASSENGER:
					System.out.println("WAIT_FOR_PASSENGER");
					// if(queueNotEmpty()){
					preparePassBoarding();
					setHostessState(HostessState.CHECK_PASSENGER);
					// }
					break;

				case CHECK_PASSENGER:
					System.out.println("CHECK_PASSENGER");
					if (!checkAndWait())
						setHostessState(HostessState.WAIT_FOR_PASSENGER);
					if (planeReadyToTakeoff()) {
						setHostessState(HostessState.READY_TO_FLY);
					}
					break;

				case READY_TO_FLY:
					System.out.println("READY_TO_FLY");

					if (!hostessJobDone()) {
						setHostessState(HostessState.WAIT_FOR_NEXT_FLIGHT);
					} else
						happyhostess = true;

					break;

				case WAIT_FOR_NEXT_FLIGHT:
					System.out.println("WAIT_FOR_NEXT_FLIGHT");
					waitForNextFlightH();
					setHostessState(HostessState.WAIT_FOR_PASSENGER);
					break;
			}
		}
	}

	/**
	 * Hostess's method. Change state of hostess and report status to log.
	 *
	 * @param state state of hostess
	 */
	private void setHostessState(HostessState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Hostess's method. Retrieves hostess's state.
	 *
	 * @return hostess's state
	 */
	private HostessState getHostessState() {
		return this.state;
	}

}
