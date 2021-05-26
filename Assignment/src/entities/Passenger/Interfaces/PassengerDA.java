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
public interface PassengerDA {

    /**
     * Passenger's method. Passenger travel to airport.
     * 
     */
    public void travelToAirport();

    /**
     * Passenger's method. Passenger enters the airport queue and waits until it's
     * his turn.
     * 
     * @param id id of the passenger
     */
    public void waitInQueue(int id);

    /**
     * Passenger's method. Checks if the passenger has been moved to the
     * documentsQueue.
     * 
     * @param id id of the passenger
     * @return a boolean representing if the passenger exists in the documentsQueue
     */
    public boolean showDocuments(int id);

    /**
     * Passenger's method. Passenger waits until it's his time to board the plane.
     * 
     * @return a boolean representing if the passenger can board the plane
     */
    public boolean waitinQueueFlight();

    /**
     * Passenger's method. Unused
     * 
     * @param id id of the passenger
     * 
     */
    public void waitForNextFlightP(int id);

}