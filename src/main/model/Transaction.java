package model;

import exceptions.ArchiveException;

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

    public Date getDate() {
        return date;
    }

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
    public void setAmount(int a) throws ArchiveException {
        if (isArchive) {
            throw new ArchiveException();
        }
        amount = a;
    }

    public void setType(String t) throws ArchiveException {
        if (isArchive) {
            throw new ArchiveException();
        }
        type = t;
    }

    public void setDate(Date d) throws ArchiveException {
        if (isArchive) {
            throw new ArchiveException();
        }
        date = d;
    }

    public void setIsArchive() {
        isArchive = true;
    }

    // EFFECTS: print out the transaction detail in one line
    public void printTransaction() {
        System.out.println(date.shortFormat() + ", donated " + amount
                + " by " + getType());
    }

}
