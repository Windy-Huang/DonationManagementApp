package persistance;

import org.json.JSONObject;
import ui.Panel;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writer that writes data to a file for storage purposes
public class Writer {

    private static final int TAB = 4;
    private PrintWriter pw;
    private String destination;

    // EFFECTS: creates a writer
    //          throws FileNotFoundException if destination file cannot
    public Writer(String s) throws FileNotFoundException {
        destination = s;
        pw = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of panel to file and close
    public void write(Panel p) {
        JSONObject json = p.toJson();
        pw.print(json.toString(TAB));
        pw.close();
    }

}
