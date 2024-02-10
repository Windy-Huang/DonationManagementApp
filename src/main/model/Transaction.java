package model;

import exceptions.ArchieveException;

// represent a transaction of amount, type, date, and archive status
public class Transaction {

    private int amount;
    private TransactionType type;
    private Date date;
    private Boolean isArchive;

    // EFFECTS: create a transaction object with date, type, amount and not archived
    public Transaction(Date d, TransactionType t, int a) {
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
        switch (type) {
            case CASH:
                return "cash";
            case DEBIT_CARD:
                return "debit";
            case CREDIT_CARD:
                return "credit";
        }
        return null;
    }

    public Date getDate() {
        return date;
    }

    // SETTERS:
    public void setAmount(int a) throws ArchieveException {
        if (isArchive) {
            throw new ArchieveException();
        }
        amount = a;
    }

    public void setType(TransactionType t) throws ArchieveException {
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
