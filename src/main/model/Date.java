package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// represent a self-declared date format with month, day, and year
public class Date {

    private static ArrayList<String> MONTH;
    private final int month;
    private final int day;
    private final int year;

    // EFFECTS: construct a date with month, day, year
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
        initiateMonth();
    }

    // EFFECTS: return the date in MM/DD/YYYY format
    public String shortFormat() {
        return (addLeadingZero(month) + "/" + addLeadingZero(day) + "/" + year);
    }

    // EFFECTS: return the date in Month day, year string format
    public String longFormat() {
        return (MONTH.get(month - 1) + " " + day + ", " + year);
    }

    // EFFECTS: add leading zero to month and day less than 10
    private String addLeadingZero(int x) {
        if (x < 10) {
            return ("0" + x);
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

    // EFFECTS: convert the date to Json syntax
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("month", month);
        obj.put("day", day);
        obj.put("year", year);
        return obj;
    }

}
