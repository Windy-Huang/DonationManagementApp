package ui;

import java.util.ArrayList;
import model.Donor;

// represent a command Panel that allows user interaction
public class Panel {

    private ArrayList<Donor> donors;
    private int totalDonation;

    // EFFECTS: create a panel with no donor and no donation
    public Panel() {
        donors = new ArrayList<Donor>();
        totalDonation = 0;
    }

    // GETTERS:
    public ArrayList<Donor> getDonors() {
        return donors;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    // MODIFIES: this
    // EFFECT: recalculate and update the total donations received
    public void updateDonation() {
        int c = 0;
        for (Donor d:donors) {
            c += d.getDonation();
        }
        totalDonation = c;
    }

    // EFFECTS: print out all the donors in the database in a one line format
    public void printDonor() {
        for (Donor d : donors) {
            System.out.println("Donor: " + d.getName() + ", Donation: " + d.getDonation());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a donor to the list in database
    public void addDonor(Donor d) {
        donors.add(d);
    }

    // REQUIRES: donors.contain(d) == true && for all transaction in donors, transaction.getIsArchive == false
    // MODIFIES: this
    // EFFECTS: remove a donor from the list
    public void removeDonor(Donor d) {
        donors.remove(d);
    }

    // MODIFIES: this
    // EFFECTS: arrange donor list in descending order by the amount donated using quicksort
    public ArrayList<Donor> quickSort(ArrayList<Donor> arr) {
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
            ArrayList<Donor> temp = quickSort(left);
            temp.add(arr.get(0));
            temp.addAll(quickSort(right));
            return temp;
        } else {
            return arr;
        }
    }

    // MODIFIES: this
    // EFFECTS: sort the donor list using quicksort
    public void sortDonor() {
        donors = quickSort(donors);
    }

    // EFFECTS: print the name of donor and their donated amount if their donation exceeds or equal to value
    //          donor is arranged in descending order by the amount donated
    public void donorOverValue(int value) {
        sortDonor();
        for (Donor d:donors) {
            if (d.getDonation() < value) {
                break;
            }
            System.out.println(d.getName() + " donated " + d.getDonation());
        }
    }

}
