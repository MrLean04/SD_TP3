/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Hostess.Interfaces;

import entities.Hostess.States.*;

/**
 *
 * @author Leandro e João
 */
public interface HostessDA {

    /**
     * Hostess's method. Hostess checks if pilot is at the transfer gate and sets
     * the checking documents flag to true.
     */
    public void preparePassBoarding();

    /**
     * Hostess's method. Hostess checks the documents of a passenger.
     * 
     * @return a boolean representing if a passenger was checked
     */
    public boolean checkAndWait();

    /**
     * Hostess's method. Hostess checks if the plane is ready to fly and if so
     * updates some variables.
     * 
     * @return a boolean representing if the plane is ready to fly
     */
    public boolean planeReadyToTakeoff();

    /**
     * Hostess's method. Hostess waits for the plane to come back from its flight.
     */
    public void waitForNextFlightH();

    // public boolean readyForCheck();
    
    /**
     * Hostess's method. Hostess checks if there's more passengers to check.
     * 
     * @return a boolean representing if the hostess job is done
     */
    public boolean hostessJobDone();

    /**
     * Hostess's method. Retrieves if the airport queue is not empty.
     * 
     * @return a boolean representing if the queue is not empty
     */
    public boolean queueNotEmpty();
}