package genericDeser.driver;

import genericDeser.util.PopulateObjects;
import genericDeser.fileOperations.Logger;

public class Driver {
    public static void main(String[] args) {
        String input_filename = "input.txt";
        int debug_value = 0;

        // parse arguments
        if (args.length < 2) {
            System.err.println("Program must have at least 2 arguments");
            System.exit(1);
        }else{
            input_filename = args[0];

            if (Integer.parseInt(args[1]) < 0 || Integer.parseInt(args[1]) > 2) {
                System.err.println("Debug value must be 0, 1, or 2\n");
            }else{
                debug_value = Integer.parseInt(args[1]);
            }
        }
        Logger.setDebugValue(debug_value);

        PopulateObjects po = new PopulateObjects();
        po.deserObjects(input_filename);
    }
}