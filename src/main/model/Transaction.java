package model;

import exceptions.ArchieveException;

// represent a transaction of amount, type, date, and archive status
public class Transaction {

    private int amount;
    private String type;
    private Date date;
    private Boolean isArchive;

    // EFFECTS: create a transaction object with date, type, amount and not archived
    public Transaction(Date d, String t, int a) {
        amount = a;
        type = t;
        date = d;
        isArchive = false;
    }

    //GETTERS:
    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    // SETTERS:
    public void setAmount(int a) throws ArchieveException {
        if (isArchive) {
            throw new ArchieveException();
        }
        amount = a;
    }

    public void setType(String t) throws ArchieveException {
        if (isArchive) {
            throw new ArchieveException();
        }
        type = t;
    }

    public void setDate(Date d) throws ArchieveException {
        if (isArchive) {
            throw new ArchieveException();
        }
        date = d;
    }

    public void setIsArchive() {
        isArchive = true;
    }

    // EFFECTS: print out the transaction detail in one like
    public void printTransaction() {
        System.out.println(date.shortFormat() + ", donated " + Integer.toString(amount)
                + " by " + getType());
    }

}
