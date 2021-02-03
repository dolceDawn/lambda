/**
 * @author allan
 * @version 1.0
 * @date Created in 6:33 PM 8/1/2019
 * @Description ${description}
 */
public enum NodeTypeEnum {
    /**
     * CORPORATE
     */
    CORPORATE {
        @Override
        public String getType() {
            return "CORPORATE";
        }
    },
    /**
     * BRANCH
     */
    BRANCH {
        @Override
        public String getType() {
            return "BRANCH";
        }
    },
    /**
     * POWERPLANT
     */
    POWERPLANT {
        @Override
        public String getType() {
            return "POWERPLANT";
        }
    },
    /**
     * CREW
     */
    CREW {
        @Override
        public String getType() {
            return "CREW";
        }
    },
    /**
     * ASSET
     */
    ASSET {
        @Override
        public String getType() {
            return "ASSET";
        }
    },
    SYSTEM {
        @Override
        public String getType() {
            return "SYSTEM";
        }
    },
    PROJECT {
        @Override
        public String getType() {
            return "PROJECT";
        }
    },
    CALCULATIONSENSOR {
        @Override
        public String getType() {
            return "CALCULATIONSENSOR";
        }
    },
    MODEL {
        @Override
        public String getType() {
            return "MODEL";
        }
    },
    RULE {
        @Override
        public String getType() {
            return "RULE";
        }
    }
    ;

    public abstract String getType();
}
