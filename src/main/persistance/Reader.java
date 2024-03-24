package persistance;

import model.Date;
import model.Donor;
import model.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Panel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents a reader that loads the data into the application via the given path
// referenced JsonSerializationDemo-Master
public class Reader {

    private final String path;
    private final JSONObject obj;

    // EFFECTS: creates a reader object that reads Json file
    //          throws IOException if error occurred from reading file from the provided path
    public Reader(String s) throws IOException {
        path = s;
        obj = new JSONObject(readFile());
    }

    // EFFECTS: reads source file as string and returns it
    //          Throws IOException if error occurred from reading file
    private String readFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: p
    // EFFECTS: modify the panel with loaded donor data
    public void build(Panel p) {
        JSONArray arr = obj.getJSONArray("donors");
        for (Object i: arr) {
            JSONObject donor = (JSONObject) i;
            addDonor(p, donor);
        }
    }

    // MODIFIES: p
    // EFFECTS: Add the donor data to the created panel
    private void addDonor(Panel p, JSONObject donor) {
        String name = donor.getString("name");
        String email = donor.getString("email");
        String phone = donor.getString("phone");
        Donor d = new Donor(name, email, phone);
        JSONArray transactions = donor.getJSONArray("transactions");
        for (Object i: transactions) {
            JSONObject transaction = (JSONObject) i;
            addTransaction(d, transaction);
        }
        p.addDonor(d);
        p.updateDonation();
    }

    // MODIFIES: donor
    // EFFECTS: Add the transaction data to the created donor
    private void addTransaction(Donor donor, JSONObject transaction) {
        int amount = transaction.getInt("amount");
        String type = transaction.getString("type");
        Boolean archive = transaction.getBoolean("archive");
        JSONObject date = transaction.getJSONObject("date");
        int month = date.getInt("month");
        int day = date.getInt("day");
        int year = date.getInt("year");
        Transaction t = new Transaction(new Date(month, day, year), type, amount);
        if (archive) {
            t.setIsArchive();
        }
        donor.addTransaction(t);
    }

}
