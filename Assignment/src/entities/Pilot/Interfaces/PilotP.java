/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Pilot.Interfaces;

import entities.Pilot.States.*;

/**
 *
 * @author Leandro e João
 */
public interface PilotP {

	/**
	 * Pilot's method. Pilot waits for all passengers to be in the plane so he can
	 * be ready to fly.
	 * 
	 * @return a boolean representing if the wait is done
	 */
	public boolean WaitForAllInBoard();

	/**
	 * Pilot's method. Pilot clears the queues in the plane for the next flight.
	 */
	public void atDestinationPoint();

	/**
	 * Pilot's method. Pilot sets some variables for his last flight.
	 */
	public void lFly();

	/**
	 * Airlift's method. Update Repository
	 */
	public void upd();

}