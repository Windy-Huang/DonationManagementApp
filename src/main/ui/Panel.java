package ui;

import java.util.ArrayList;
import model.Donor;

// represent a command Panel that allows user interaction
public class Panel {

    private ArrayList<Donor> donors;

    // EFFECTS: create a panel with no donor
    public Panel() {
        donors = new ArrayList<Donor>();
    }

    // GETTERS:
    public ArrayList<Donor> getDonors() {
        return donors;
    }

    // MODIFIES: this
    // EFFECTS: adds a donor to the list in database
    public void addDonor(Donor d) {
        donors.add(d);
    }

    // MODIFIES: this
    // EFFECTS: if intended donor exist in list, removes a donor based on their name
    public void removeDonor(String n) {
        int a = -1;
        for (int i = 0; i <= donors.size(); i++) {
            if (donors.get(i).getName() == n) {
                a = i;
            }
        }
        if (a != -1) {
            donors.remove(a);
        }
    }

    // MODIFIES: this
    // EFFECTS: sort the donor list so donor is arranged in descending order by the amount donated using quicksort
    public ArrayList<Donor> sortDonor(ArrayList<Donor> arr) {
        int size = arr.size();
        if (size > 1) {
            int begin = arr.get(0).getDonation();
            ArrayList<Donor> left = new ArrayList<Donor>();
            ArrayList<Donor> right = new ArrayList<Donor>();
            for (int i = 1; i < size; i++) {
                if (arr.get(i).getDonation() > begin) {
                    left.add(arr.get(i));
                } else {
                    right.add(arr.get(i));
                }
            }
            ArrayList<Donor> temp = new ArrayList<Donor>();
            temp.addAll(sortDonor(left));
            temp.add(arr.get(0));
            temp.addAll(sortDonor(right));
            return temp;
        } else {
            return arr;
        }
    }

    // EFFECTS: print the name of donor and their donated amount if their donation exceeds or equal to value
    //          donor is arranged in descending order by the amount donated
    public void donerOverValue(int value) {
        donors = sortDonor(donors);
        for (Donor d:donors) {
            if (d.getDonation() < value) {
                break;
            }
            System.out.println(d.getName() + " donated " + Integer.toString(d.getDonation()));
        }
    }
}
