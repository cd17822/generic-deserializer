package genericDeser.fileOperations;

import genericDeser.fileOperations.Logger;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileProcessor {
    FileReader reader;
    BufferedReader buffReader;

	/**
	 * Creates new FileProcessor object and opens FileReader and
	 * BufferedReader objects with @param filename
	 */
    public FileProcessor(String filename) {
        Logger.writeMessage("FileProcessor constructed\n", Logger.DebugLevel.CONSTRUCTOR);

        try {
            reader = new FileReader(filename);
            buffReader = new BufferedReader(reader);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            //nothing
        }
    }

	/**
	 * @return String that is the current line of the input file
	 */
    public String readLine() {
        String line = "";

        try {
            line = buffReader.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            // nothing
        }

        return line;
    }

	/**
	 * @return void
	 */
    public void finish() {
        try {
            buffReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            // nothing
        }
    }
}
