  
package entities.Passenger.States;

/**
 *
 * @author Leandro e João
 */
public enum PassengerState {

    //Customer stats: GTA , IQ , IF ,AD
    GOING_TO_AIRPORT {
        @Override
        public String toString() {
            return "GTA"; //return "Going to airport.";
        }
    },
    IN_QUEUE {
        @Override
        public String toString() {
            return "IQ"; // return "In queue.";
        }
    },
    IN_FLIGHT {
        @Override
        public String toString() {
            return "IF"; //return "In flight.";
        }
    },
    AT_DESTINATION {
        @Override
        public String toString() {
            return "AD"; // return "At destination.";
        }
    };
}