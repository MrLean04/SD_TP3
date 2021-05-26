  
package entities.Hostess.States;

/**
 *
 * @author Leandro e João
 */
public enum HostessState {

    //Customer stats: WFNF , WFP , CP , RTF
    WAIT_FOR_NEXT_FLIGHT {
        @Override
        public String toString() {
            return "WFNF"; //return "Wait for next flight.";
        }
    },
    WAIT_FOR_PASSENGER {
        @Override
        public String toString() {
            return "WFP"; // return "Wait for passenger.";
        }
    },
    CHECK_PASSENGER {
        @Override
        public String toString() {
            return "CP"; //return "Check passenger.";
        }
    },
    READY_TO_FLY {
        @Override
        public String toString() {
            return "RTF"; // return "Ready to fly.";
        }
    };
}