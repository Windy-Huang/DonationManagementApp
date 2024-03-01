package persistance;

import model.Date;
import model.Donor;
import model.Transaction;
import org.junit.jupiter.api.Test;
import ui.Panel;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Panel panel = new Panel();
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.write(panel);
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Panel panel = new Panel();
            Writer writer = new Writer("./data/testWriterEmpty.json");
            writer.write(panel);

            Reader reader = new Reader("./data/testReaderEmpty.json");
            panel = reader.build();
            assertEquals(0, panel.getDonors().size());
            assertEquals(0, panel.getTotalDonation());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Panel panel = new Panel();
            Donor d1 = new Donor("Windy", "163.com", "604");
            d1.addTransaction(new Transaction(new Date(10, 4,2024), "cash", 100));
            panel.addDonor(d1);
            panel.addDonor(new Donor("Lillian", "outlook.com", "778"));
            Writer writer = new Writer("./data/testWriter.json");
            writer.write(panel);

            Reader reader = new Reader("./data/testWriter.json");
            panel = reader.build();
            assertEquals(2, panel.getDonors().size());
            panel.sortDonor();
            d1 = panel.getDonors().get(0);
            assertEquals("Windy", d1.getName());
            assertEquals("Lillian", panel.getDonors().get(1).getName());
            assertEquals(100, panel.getTotalDonation());
            assertEquals("10/04/2024", d1.getTransactions().get(0).getDate().shortFormat());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
