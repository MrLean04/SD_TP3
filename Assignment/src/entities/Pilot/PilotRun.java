package entities.Pilot;



/**
 * Main customer class. Instantiates the customers.
 * 
 * @author Leandro e João
 */
public class PilotRun {

    /**
     * Main customer class. Instantiates the customers.
     * @param args -
     */
    public static void main(String[] args) {
        
        Pilot pilot = new Pilot();
        pilot.start();
        try {
            pilot.join(0);
        }catch(InterruptedException ex) {
                System.out.println("pilot died!");
            }
        }
    }