package model;

import exceptions.IndexException;

import java.util.ArrayList;

// represent a donor with name, phone, email, total donation (in $), and list of transaction
public class Donor {

    private String name;
    private String email;
    private String phone;
    private int donation;
    private ArrayList<Transaction> transactions;

    // EFFECTS: create a donor with name, email, phone, with no amount donated
    public Donor(String n, String s, String p) {
        name = n;
        email = s;
        phone = p;
        donation = 0;
        transactions = new ArrayList<Transaction>();
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

    // EFFECTS: print out all the donation transaction of donor
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
    // EFFECTS: add a transaction to the list of transaction, and increase donation
    public void addTransaction(Transaction t) {
        transactions.add(t);
        donation += t.getAmount();
    }

    // MODIFIES: this
    // EFFECTS: remove a transaction at the specified index (1 based) from the list of transaction
    //          decrement donation accordingly
    public void removeTransaction(int n) throws IndexException {
        if (n >= transactions.size()) {
            throw new IndexException();
        }
        donation -= transactions.get(n - 1).getAmount();
        transactions.remove(n - 1);
    }

}
