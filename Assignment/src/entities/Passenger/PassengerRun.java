package entities.Passenger;


/**
 * Main customer class. Instantiates the customers.
 * 
 * @author Leandro e João
 */
public class PassengerRun {

    /**
     * Main customer class. Instantiates the customers.
     * @param args -
     */
    public static void main(String[] args) {
        
        Passenger[] passenger = new Passenger[21];

        for(int i = 0; i < 21; i++) {
            passenger[i] = new Passenger(i);
            passenger[i].start();
        }

        for(int i = 0; i < 21; i++) {
            try {
                passenger[i].join();
            }
            catch(InterruptedException ex) {
                System.out.println("Passenger " + i + " is dead!");
            }
        }
    }
}