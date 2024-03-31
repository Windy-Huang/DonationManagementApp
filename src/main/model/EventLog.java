package model;

import pattern.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// represent a log of database events
// referenced CPSC 210 alarm system project
public class EventLog implements Iterable<Event>, Observer {

    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: construct an empty log
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: instantiate the eventLog if it does not exist previously
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: add an event to the log
    private void logEvent(Event e) {
        events.add(e);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

    public void printLog() {
        System.out.println("EventLog");
        System.out.println("");

        for (Event next : events) {
            System.out.println(next.getDate());
            System.out.println(next.getDescription());
            System.out.println("");
        }

        System.out.println("End of Session");
    }

    @Override
    public void update(Object o) {
        String s = (String) o;
        logEvent(new Event(s));
    }
}
