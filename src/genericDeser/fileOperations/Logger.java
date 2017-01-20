package genericDeser.fileOperations;

public class Logger {

    public static enum DebugLevel {
        NONE, CONSTRUCTOR, TREE_STEPS
    };

    private static DebugLevel debug_level;

    public static void setDebugValue(int levelIn) {
        switch(levelIn) {
           case 2  : debug_level = DebugLevel.TREE_STEPS; break;
           case 1  : debug_level = DebugLevel.CONSTRUCTOR; break;
           default : debug_level = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue(DebugLevel levelIn) {
        debug_level = levelIn;
    }

    public static void writeMessage(String message, DebugLevel levelIn) {
        if (levelIn == debug_level) {
            System.out.println(message);
        }
    }

    /**
     * @return the debug level as a String
     */
    public String toString() {
        return "Debug Level is " + debug_level;
    }
}
