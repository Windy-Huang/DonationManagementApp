package model;

import java.util.Calendar;
import java.util.Date;

// represent an event in the database
// referenced CPSC 210 alarm system project
public class Event {
    private Date dateLogged;
    private String description;

    // EFFECTS: Creates an event with the given description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // GETTERS:
    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

}
