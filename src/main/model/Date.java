package model;

import java.util.ArrayList;

// represent a self-declared date format with month, day, and year
public class Date {

    private static ArrayList<String> MONTH;
    private int month;
    private int day;
    private int year;

    // EFFECTS: construct a date with month, day, year
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
        initiateMonth();
    }

    // EFFECTS: return the date in MM/DD/YYYY format
    public String shortFormat() {
        String s = addLeadingZero(month) + "/" + addLeadingZero(day) + "/" + Integer.toString(year);
        return s;
    }

    // EFFECTS: return the date in Month day, year string format
    public String longFormat() {
        String s = MONTH.get(month - 1) + " " + Integer.toString(day) + ", " + Integer.toString(year);
        return s;
    }

    // EFFECTS: add leading zero to month and day less than 10
    private String addLeadingZero(int x) {
        if (x < 10) {
            return ("0" + Integer.toString(x));
        } else {
            return (Integer.toString(x));
        }
    }

    // MODIFIES: this
    // EFFECTS: initiate the MONTH array list
    private void initiateMonth() {
        MONTH = new ArrayList<String>();
        MONTH.add("January");
        MONTH.add("February");
        MONTH.add("March");
        MONTH.add("April");
        MONTH.add("May");
        MONTH.add("June");
        MONTH.add("July");
        MONTH.add("August");
        MONTH.add("September");
        MONTH.add("October");
        MONTH.add("November");
        MONTH.add("December");
    }

}
