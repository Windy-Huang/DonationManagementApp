package persistance;

import model.Donor;
import model.Transaction;
import org.junit.jupiter.api.Test;
import ui.Panel;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    public void testReaderNoFile() {
        try {
            Reader reader = new Reader("./data/notExist.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyFile() {
        try {
            Reader reader = new Reader("./data/testReaderEmpty.json");
            Panel panel = reader.build();
            assertEquals(0, panel.getTotalDonation());
            assertEquals(0, panel.getDonors().size());
        } catch (IOException e) {
            fail("file path exist");
        }
    }

    @Test
    public void testReaderLoadFile() {
        try {
            Reader reader = new Reader("./data/testReader.json");
            Panel panel = reader.build();
            assertEquals(160, panel.getTotalDonation());
            assertEquals(2, panel.getDonors().size());
            panel.sortDonor();
            Donor d1 = panel.getDonors().get(0);
            testDonorLoad(d1, "Windy", "163.com", "123456789", 110, 2);
            testTransaction(d1.getTransactions().get(0), 100, "cash", "12/05/2024", false);
            testTransaction(d1.getTransactions().get(1), 10, "debit", "03/01/2023", true);
            Donor d2 = panel.getDonors().get(1);
            testDonorLoad(d2, "Lillian", "gmail.com", "7780001111", 50, 1);
            testTransaction(d2.getTransactions().get(0), 50, "cash", "12/04/2021", false);
        } catch (IOException e) {
            fail("file path exist");
        }
    }

    private void testDonorLoad(Donor d, String name, String email, String phone, int amount, int size) {
        assertEquals(name, d.getName());
        assertEquals(email, d.getEmail());
        assertEquals(phone, d.getPhone());
        assertEquals(amount, d.getDonation());
        assertEquals(size, d.getTransactions().size());
    }

    private void testTransaction(Transaction t, int amount, String type, String date, Boolean archive) {
        assertEquals(amount, t.getAmount());
        assertEquals(type, t.getType());
        assertEquals(date, t.getDate().shortFormat());
        assertEquals(archive, t.getIsArchive());
    }

}
