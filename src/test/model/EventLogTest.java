package model;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// referenced the EventLogTest class from CPSC 210 Alarm System
public class EventLogTest {

    @BeforeEach
    public void loadEvents() {
        EventLog el = EventLog.getInstance();
        el.update("A1");
        el.update("A2");
        el.update("A3");
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

}