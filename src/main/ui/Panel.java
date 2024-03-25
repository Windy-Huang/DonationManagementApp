package ui;

import java.util.ArrayList;

import exceptions.DuplicateException;
import model.Donor;
import model.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

// represent a command Panel that allows user interaction
public class Panel {

    private ArrayList<Donor> donors;
    private int totalDonation;

    // EFFECTS: create a panel with no donor and no donation
    public Panel() {
        donors = new ArrayList<>();
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

    // EFFECTS: return the donors in an 2D array
    public String[][] donorArray() {
        sortDonor();
        String[][] arr = new String[donors.size()][2];
        for (int i = 0; i < donors.size(); i++) {
            arr[i][0] = donors.get(i).getName();
            arr[i][1] = Integer.toString(donors.get(i).getDonation());
        }
        return arr;
    }

    // MODIFIES: this
    // EFFECTS: adds a donor to the list in database
    //          if donor already exist, then do nothing
    public void addDonor(Donor d) throws DuplicateException {
        if (donors.contains(d)) {
            throw new DuplicateException();
        }
        donors.add(d);
    }

    // EFFECTS: return the index of the donor in the list
    //          if donor does not exist, return null
    public Object findDonor(Donor d) {
        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).equals(d)) {
                return i;
            }
        }
        return null;
    }

    // REQUIRES: donors.contain(d) == true && for all transaction in donors, transaction.getIsArchive == false
    // MODIFIES: this
    // EFFECTS: remove a donor from the list
    public void removeDonor(Donor d) {
        donors.remove(d);
    }

    // MODIFIES: this
    // EFFECTS: arrange donor list in descending order by the amount donated using quicksort
    private ArrayList<Donor> quickSort(ArrayList<Donor> arr) {
        int size = arr.size();
        if (size > 1) {
            int begin = arr.get(0).getDonation();
            ArrayList<Donor> left = new ArrayList<>();
            ArrayList<Donor> right = new ArrayList<>();
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

    // EFFECTS: return 2D array donor and their donated amount if their donation exceeds or equal to value
    //          donor is arranged in descending order by the amount donated
    public String[][] donorOverValue(int value) {
        ArrayList<Donor> arr = new ArrayList<>();
        sortDonor();
        for (Donor d:donors) {
            if (d.getDonation() < value) {
                break;
            }
            arr.add(d);
        }
        String[][] result = new String[arr.size()][2];
        for (int i = 0; i < arr.size(); i++) {
            result[i][0] = arr.get(i).getName();
            result[i][1] = Integer.toString(arr.get(i).getDonation());
        }
        return result;
    }

    // EFFECTS: convert the panel to JSON syntax
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Donor d:donors) {
            JSONObject donor = new JSONObject();
            donor.put("name",d.getName());
            donor.put("email",d.getEmail());
            donor.put("phone",d.getPhone());
            donor.put("transactions", d.toJson());
            arr.put(donor);
        }
        obj.put("donors", arr);
        return obj;
    }

    // EFFECTS: return the transaction statistic as an integer array
    public int[] transactionData() {
        int[] arr = new int[12];
        for (Donor d:donors) {
            ArrayList<Transaction> transactions = d.getTransactions();
            for (Transaction t:transactions) {
                if (t.getDate().getMonth() <= 6) {
                    upperHelper(arr, t);
                } else {
                    lowerHelper(arr, t);
                }
            }
        }
        return arr;
    }

    // MODIFIES: arr
    // EFFECTS: help place the transaction amount in Jan - Jun
    private void upperHelper(int[] arr, Transaction t) {
        switch (t.getDate().getMonth()) {
            case 1:
                arr[0] += t.getAmount();
                break;
            case 2:
                arr[1] += t.getAmount();
                break;
            case 3:
                arr[2] += t.getAmount();
                break;
            case 4:
                arr[3] += t.getAmount();
                break;
            case 5:
                arr[4] += t.getAmount();
                break;
            case 6:
                arr[5] += t.getAmount();
                break;
        }
    }

    // MODIFIES: arr
    // EFFECTS: help place the transaction amount in Jul - Dec
    private void lowerHelper(int[] arr, Transaction t) {
        switch (t.getDate().getMonth()) {
            case 7:
                arr[6] += t.getAmount();
                break;
            case 8:
                arr[7] += t.getAmount();
                break;
            case 9:
                arr[8] += t.getAmount();
                break;
            case 10:
                arr[9] += t.getAmount();
                break;
            case 11:
                arr[10] += t.getAmount();
                break;
            case 12:
                arr[11] += t.getAmount();
                break;
        }
    }

}
