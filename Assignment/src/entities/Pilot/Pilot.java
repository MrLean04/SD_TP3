package entities.Pilot;

//import java.lang.ref.Cleaner;
import java.util.*;

import entities.Pilot.States.PilotState;


/**
 *
 * @author Leandro e Jo�o
 */

public class Pilot extends Thread {

	private PilotState state;
	private boolean happypilot = false;


	/**
	 * Pilot's constructor.
	 *
	 */
	public Pilot() {
		//
	}


	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private void zeroCount() {

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void parkAtTransfer() {

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void readyForBoarding() {

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void WaitForBoarding() {

	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private boolean WaitForAllInBoard() {
		return false;
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void upd() {
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void atDestinationPoint() {

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void everyoneStops() {

	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean AnnounceArrival() {
		return false;
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean goBack() {
		return false;
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean lastF() {
		return false;
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void last() {

	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void lFly() {

	}

	@Override
	public void run() {
		this.setPilotState(PilotState.AT_TRANSFER_GATE);
		while (!happypilot) {
			switch (this.state) {
				case AT_TRANSFER_GATE:
					System.out.println("AT_TRANSFER_GATE ");
					zeroCount();
					parkAtTransfer();
					setPilotState(PilotState.READY_FOR_BOARDING);
					break;

				case READY_FOR_BOARDING:
					System.out.println(" READY_FOR_BOARDING ");
					readyForBoarding();
					WaitForBoarding();
					setPilotState(PilotState.WAIT_FOR_BOARDING);
					break;

				case WAIT_FOR_BOARDING:
					System.out.println(" WAIT_FOR_BOARDING ");
					if (WaitForAllInBoard()) {
						setPilotState(PilotState.FLYING_FORWARD);
					}
					break;

				case FLYING_FORWARD:
					System.out.println("FLYING_FORWARD ");
					upd();
					setPilotState(PilotState.DEBOARDING);
					break;

				case DEBOARDING:
					System.out.println("DEBOARDING ");
					atDestinationPoint();
					everyoneStops();
					setPilotState(PilotState.FLYING_BACK);
					break;

				case FLYING_BACK:
					System.out.println("FLYING BACK");
					if (AnnounceArrival()) {
						System.out.println("End program");
						happypilot = true;
						// System.out.println("morri");
					}

					else {
						if (goBack()) {
							if (lastF()) {
								last();
								System.out.println("last f");
								lFly();
							}

							setPilotState(PilotState.AT_TRANSFER_GATE);
						}
					}

					break;
			}
		}
	}

	/**
	 * Pilot's method. Change state of pilot and report status to log.
	 *
	 * @param state state of Pilot
	 */
	private void setPilotState(PilotState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Pilot's method. Retrieves pilot's state.
	 *
	 * @return pilot's state
	 */
	private PilotState getPilotState() {
		return this.state;
	}

}
