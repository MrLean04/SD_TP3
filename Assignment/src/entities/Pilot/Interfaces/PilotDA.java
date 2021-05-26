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
public interface PilotDA {
    /**
     * Pilot's method. Pilot arrives at transfer gate
     * 
     */
    public void parkAtTransfer();

    /**
     * Pilot's method. Pilot is ready for boarding
     * 
     */
    public void readyForBoarding();

    /**
     * Pilot's method. Pilot waits for all passengers to board.
     * 
     */
    public void WaitForBoarding();

    /**
     * Pilot's method. Hostess stops passengers from boarding the plane
     * 
     */
    public void everyoneStops();

    /**
     * Pilot's method. Pilot sets the last flight flag to true.
     */
    public void last();
}