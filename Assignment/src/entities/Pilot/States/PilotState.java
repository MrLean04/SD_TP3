package entities.Pilot.States;

/**
 *
 * @author Leandro e João
 */
public enum PilotState {

    //Pilot stats: ATG, RFB, WFB , FF , D , FB
    AT_TRANSFER_GATE {
        @Override
        public String toString() {
            return "ATG"; //"At transfer gate.";
        }
    },
    READY_FOR_BOARDING {
        @Override
        public String toString() {
            return "RFB"; // "Ready for boarding.";
        }
    },
    WAIT_FOR_BOARDING {
        @Override
        public String toString() {
            return "WFB"; //Wait for boarding.";
        }
    },
    FLYING_FORWARD {
        @Override
        public String toString() {
            return "FF"; // return "Flying forward.";
        }
    },
    DEBOARDING {
        @Override
        public String toString() {
            return "D"; // return "Deboarding.";
        }
    },

    FLYING_BACK {
        @Override
        public String toString() {
            return "FB"; // return "Flying back.";
        }
    };
}