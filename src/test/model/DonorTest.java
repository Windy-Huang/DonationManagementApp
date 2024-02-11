package model;

import exceptions.ArchiveException;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DonorTest {

    private Donor d1;
    private Date date;
    private Transaction t1;
    private Transaction t2;
    private final PrintStream systemOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        d1 = new Donor("a", "e1", "p1");
        date = new Date(1,8,2024);
        t1 = new Transaction(date, "cash", 10);
        t2 = new Transaction(date, "debit", 500);
        t2.setIsArchive();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(systemOutput);
    }

    @Test
    public void testConstructor() {
        assertEquals("a", d1.getName());
        assertEquals("e1", d1.getEmail());
        assertEquals("p1", d1.getPhone());
        assertEquals(0, d1.getDonation());
        assertTrue(d1.getTransactions().isEmpty());
    }

    @Test
    public void testPrintTransactionsEmpty() {
        d1.printTransactions();
        assertEquals("There is currently no transaction made by this donor.", outputStream.toString().trim());
    }

    @Test
    public void testPrintTransactionsOnce() {
        d1.addTransaction(t1);
        d1.printTransactions();
        assertEquals("01/08/2024, donated 10 by cash", outputStream.toString().trim());
    }

    @Test
    public void testPrintTransactionsMultiple() {
        d1.addTransaction(t1);
        d1.addTransaction(t2);
        d1.printTransactions();
        assertEquals("01/08/2024, donated 10 by cash\n" +
                "01/08/2024, donated 500 by debit", outputStream.toString().trim());
    }

    @Test
    public void testAddTransactionOnce() {
        d1.addTransaction(t1);
        assertEquals(1, d1.getTransactions().size());
        assertEquals(10, d1.getDonation());
    }

    @Test
    public void testAddTransactionMultiple() {
        d1.addTransaction(t1);
        d1.addTransaction(t2);
        assertEquals(2, d1.getTransactions().size());
        assertEquals(510, d1.getDonation());
    }

    @Test
    public void testRemoveTransaction() throws ArchiveException {
        d1.addTransaction(t1);
        d1.removeTransaction(t1);
        assertEquals(0, d1.getTransactions().size());
        assertEquals(0, d1.getDonation());
    }

    @Test
    public void testRemoveTransactionEx() {
        d1.addTransaction(t2);
        assertThrows(ArchiveException.class, () -> {
            d1.removeTransaction(t2);
        });
        assertEquals(1, d1.getTransactions().size());
        assertEquals(500, d1.getDonation());
    }

    @Test
    public void testChangeTransaction() throws ArchiveException{
        d1.addTransaction(t1);
        d1.changeTransaction(t1, new Date(5,1,2022), "credit", 50);
        assertEquals(1, d1.getTransactions().size());
        assertEquals(50, d1.getDonation());
    }

    @Test
    public void testChangeTransactionEx() {
        d1.addTransaction(t2);
        assertThrows(ArchiveException.class, () -> {
            d1.changeTransaction(t2, new Date(5,1,2022), "credit", 50);
        });
        assertEquals(1, d1.getTransactions().size());
        assertEquals(500, d1.getDonation());
    }

}
