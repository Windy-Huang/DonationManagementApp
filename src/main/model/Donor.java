package model;

import exceptions.ArchiveException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

// represent a donor with unique name, phone, email, total donation (in $), and list of transactions
public class Donor {

    private final String name;
    private final String email;
    private final String phone;
    private int donation;
    private ArrayList<Transaction> transactions;

    // EFFECTS: create a donor with name, email, phone, with no amount donated, and empty transaction list
    public Donor(String n, String s, String p) {
        name = n;
        email = s;
        phone = p;
        donation = 0;
        transactions = new ArrayList<>();
    }

    // GETTERS:
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getDonation() {
        return donation;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // EFFECTS: return transactions in 2D array
    public String[][] transactionArray() {
        String[][] arr = new String[transactions.size()][2];
        for (int i = 0; i < transactions.size(); i++) {
            arr[i][0] = transactions.get(i).getDate().shortFormat();
            arr[i][1] = Integer.toString(transactions.get(i).getAmount());
        }
        return arr;
    }

    // EFFECTS: print out all the donation transaction of donor. If there is no transaction, notify the user
    public void printTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("There is currently no transaction made by this donor.");
        } else {
            for (Transaction t : transactions) {
                t.printTransaction();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add a transaction to the list of transaction, and increase donation accordingly
    public void addTransaction(Transaction t) {
        transactions.add(t);
        donation += t.getAmount();
    }

    // REQUIRES: transactions.contain(t) == true
    // MODIFIES: this
    // EFFECTS: remove a transaction from the list of transaction
    //          decrement donation accordingly
    public void removeTransaction(Transaction t) throws ArchiveException {
        if (t.getIsArchive()) {
            throw new ArchiveException();
        }
        donation -= t.getAmount();
        transactions.remove(t);
    }

    // REQUIRES: transactions.contain(t) == true
    // MODIFIES: this
    // EFFECTS: reset or reinitialize the inputted transaction t
    //          if t is archived, throw ArchiveException
    public void changeTransaction(Transaction t, Date d, String type, int amount) throws ArchiveException {
        t.setDate(d);
        t.setType(type);
        donation -= t.getAmount();
        t.setAmount(amount);
        donation += t.getAmount();
    }

    // EFFECTS: convert the list of transaction to Json syntax
    public JSONArray toJson() {
        JSONArray arr = new JSONArray();
        for (Transaction t:transactions) {
            JSONObject transaction = new JSONObject();
            transaction.put("amount",t.getAmount());
            transaction.put("type",t.getType());
            transaction.put("date",t.getDate().toJson());
            transaction.put("archive", t.getIsArchive());
            arr.put(transaction);
        }
        return arr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Donor donor = (Donor) o;
        return name.equalsIgnoreCase(donor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
