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
public interface PilotDSA {

    /**
     * Pilot's method. When all passengers have been transported to the destination
     * airport the pilot will announce his final arrival.
     * 
     * @return a boolean representing if all passengers have been transported
     */
    public boolean AnnounceArrival();

    /**
     * Pilot's method. Pilot will wait for all the passengers to get on the
     * destination airport so he can fly back.
     * 
     * @return a boolean representing if it's time to fly back
     */
    public boolean goBack();

    /**
     * Pilot's method. Pilot checks if it's the last flight. 
     * @return a boolean representing if it the next flight is the last.
     */
    public boolean lastF();

    /**
     * Pilot's method. Used to put the variable with the value null.
     */
    public void zeroCount();
}