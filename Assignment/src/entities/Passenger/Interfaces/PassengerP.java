/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Passenger.Interfaces;

import entities.Passenger.States.*;

/**
 *
 * @author Leandro e João
 */
public interface PassengerP {

	/**
	 * Passenger's method. Passenger boards the plane (enters the inPlane queue).
	 * 
	 * @param id id of the passenger
	 * @return a boolean representing if the passenger is done boarding
	 */
	public boolean BoardThePlane(int id);

	/**
	 * Passenger's method. Passenger waits to the end of flight.
	 */
	public void WaitingForEndOfFlight();
}