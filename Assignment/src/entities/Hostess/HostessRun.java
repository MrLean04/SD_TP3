package entities.Hostess;


/**
 * Main customer class. Instantiates the customers.
 * 
 * @author Leandro e João
 */
public class HostessRun {

    /**
     * Main customer class. Instantiates the customers.
     * @param args -
     */
    public static void main(String[] args) {
        
        Hostess hostess = new Hostess();
        hostess.start();
        try {
            hostess.join(0);
        }catch(InterruptedException ex) {
                System.out.println("hostess died!");
            }
        }
    }
