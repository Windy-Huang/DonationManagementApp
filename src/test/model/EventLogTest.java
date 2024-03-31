package model;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// referenced the EventLogTest class from CPSC 210 Alarm System
public class EventLogTest {

    private final PrintStream systemOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void loadEvents() {
        EventLog el = EventLog.getInstance();
        el.update("A1");
        el.update("A2");
        el.update("A3");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(systemOutput);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertEquals("A1", l.get(0).getDescription());
        assertEquals("A2", l.get(1).getDescription());
        assertEquals("A3", l.get(2).getDescription());
    }

    @Test
    public void testPrintLog() {
        EventLog el = EventLog.getInstance();
        el.printLog();
        String s = outputStream.toString().trim();
        assertTrue(s.startsWith("EventLog"));
        assertTrue(s.contains("A1"));
        assertTrue(s.contains("A2"));
        assertTrue(s.contains("A3"));
        assertTrue(s.endsWith("End of Session"));
    }

}