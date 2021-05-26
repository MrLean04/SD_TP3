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
public interface PassengerDSA {

    /**
     * Passenger's method. Passenger removes himself from the inDeboarding queue.
     * 
     * @param id id of the passenger
     */
    public void Deboarding(int id);

    /**
     * Pilot's method. Pilot adds a passengers to the destination airport queues.
     * 
     * @param id id of the passenger
     */
    public void atAirport(int id);
}