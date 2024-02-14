package model;

import exceptions.ArchiveException;
import exceptions.EmptyException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TransactionTest {

    private Date date;
    private Transaction t1;
    private Transaction t2;
    private final PrintStream systemOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
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
        assertEquals(date, t1.getDate());
        assertEquals("cash", t1.getType());
        assertEquals(10, t1.getAmount());
        assertFalse(t1.getIsArchive());
    }

//    @Test
//    public void testSetAmount() throws ArchiveException {
//       t1.setAmount(20);
//       assertEquals(20, t1.getAmount());
//    }

    @Test
    public void testSetAmount() {
        try {
            t1.setAmount(20);
        } catch (ArchiveException e) {
            fail("should not throw exception");
        }
        assertEquals(20, t1.getAmount());
    }

//    @Test
//    public void testSetAmountEx() {
//        assertThrows(ArchiveException.class, () -> {
//            t2.setAmount(20);
//        });
//        assertEquals(500, t2.getAmount());
//    }

    @Test
    public void testSetAmountEx() {
        try {
            t2.setAmount(20);
            fail("should throw exception");
        } catch (ArchiveException e) {
        }
        assertEquals(500, t2.getAmount());
    }

//    @Test
//    public void testSetType() throws ArchiveException {
//        t1.setType("debit");
//        assertEquals("debit", t1.getType());
//    }

    @Test
    public void testSetType() {
        try {
            t1.setType("debit");
        } catch (ArchiveException e) {
            fail("should not throw exception");
        }
        assertEquals("debit", t1.getType());
    }

//    @Test
//    public void testSetTypeEx() {
//        assertThrows(ArchiveException.class, () -> {
//            t2.setType("cash");
//        });
//        assertEquals("debit", t2.getType());
//    }

    @Test
    public void testSetTypeEx() {
        try {
            t2.setType("cash");
            fail("should throw exception");
        } catch (ArchiveException e) {
        }
        assertEquals("debit", t2.getType());
    }

//    @Test
//    public void testSetDate() throws ArchiveException {
//        t1.setDate(new Date(2,8,2024));
//        assertEquals("02/08/2024", t1.getDate().shortFormat());
//    }

    @Test
    public void testSetDate() {
        try {
            t1.setDate(new Date(2,8,2024));
        } catch (ArchiveException e) {
            fail("should throw exception");
        }
        assertEquals("02/08/2024", t1.getDate().shortFormat());
    }

//    @Test
//    public void testSetDateEx() {
//        assertThrows(ArchiveException.class, () -> {
//            t2.setDate(new Date(2,8,2024));
//        });
//        assertEquals(date, t2.getDate());
//    }

    @Test
    public void testSetDateEx() {
        try {
            t2.setDate(new Date(2,8,2024));
            fail("should throw exception");
        } catch (ArchiveException e) {
        }
        assertEquals(date, t2.getDate());
    }

    @Test
    public void testPrintTransactionOnce() {
        t1.printTransaction();
        assertEquals("01/08/2024, donated 10 by cash", outputStream.toString().trim());
    }

    @Test
    public void testPrintTransactionMultiple() {
        t1.printTransaction();
        t2.printTransaction();
        assertEquals("01/08/2024, donated 10 by cash\n"
                + "01/08/2024, donated 500 by debit", outputStream.toString().trim());
    }

}
