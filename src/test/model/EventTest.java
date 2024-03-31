package model;

import org.junit.jupiter.api.*;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

// referenced the EventTest class from CPSC 210 Alarm System
public class EventTest {

    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("A new transaction added to kerry");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("A new transaction added to kerry", e.getDescription());
        assertEquals(d.toString(), e.getDate().toString());
    }

}
