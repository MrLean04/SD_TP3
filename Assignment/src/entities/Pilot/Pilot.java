package entities.Pilot;

import java.rmi.RemoteException;
import java.util.*;

import entities.Pilot.States.PilotState;
import interfaces.DepartureInterface;
import interfaces.PlaneInterface;
import interfaces.DestinationInterface;



/**
 *
 * @author Leandro e Jo�o
 */

public class Pilot extends Thread {

	private PilotState state;
	private boolean happypilot = false;
	private DepartureInterface departureInterface;
	private PlaneInterface planeInterface;
	private DestinationInterface destinationInterface;


	/**
	 * Pilot's constructor.
	 *
	 */
	public Pilot(DepartureInterface departureInterface, PlaneInterface planeInterface, DestinationInterface destinationInterface) {
		//
		this.departureInterface = departureInterface;
		this.planeInterface=planeInterface;
		this.destinationInterface=destinationInterface;
	}


	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private void zeroCount() {
        try {
            destinationInterface.zeroCount();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void parkAtTransfer() {
        try {
            departureInterface.parkAtTransfer();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void readyForBoarding() {
        try {
            departureInterface.readyForBoarding();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void WaitForBoarding() {
        try {
            departureInterface.WaitForBoarding();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private boolean WaitForAllInBoard() {
		boolean temp = false;
        try {
            temp = planeInterface.WaitForAllInBoard();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void upd() {
        try {
            planeInterface.upd();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void atDestinationPoint() {
        try {
            planeInterface.atDestinationPoint();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void everyoneStops() {
        try {
            departureInterface.everyoneStops();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean AnnounceArrival() {
		boolean temp = false;
        try {
            temp = destinationInterface.AnnounceArrival();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean goBack() {
		boolean temp = false;
        try {
            temp = destinationInterface.goBack();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean lastF() {
		boolean temp = false;
        try {
            temp = destinationInterface.lastF();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
        return temp;

	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void last() {
        try {
            departureInterface.last();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void lFly() {
		try {
            planeInterface.lFly();
        } catch (RemoteException e) {
            System.err.println("Excepção na invocação remota de método" + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

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
