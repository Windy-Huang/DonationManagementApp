package ui;

import exceptions.ArchieveException;
import model.Date;
import model.Donor;
import model.Transaction;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        Date d = new Date(1,5,2018);
//        Transaction t1 = new Transaction(d, TransactionType.CASH, 99);
//        Transaction t2 = new Transaction(d, TransactionType.CASH, 100);
//        Transaction t3 = new Transaction(d, TransactionType.CASH, 101);
//        Transaction t4 = new Transaction(d, TransactionType.CASH, 102);
////        try {
////            t.setAmount(100);
////        } catch (ArchieveException e) {
////            System.out.println("transaction archieved");
////        }
////        System.out.println(t.getAmount());
//
//
//        Donor d1 = new Donor("d1","...", "...");
//        d1.addTransaction(t1);
//        Donor d2 = new Donor("d2","...", "...");
//        d2.addTransaction(t2);
//        Donor d3 = new Donor("d3","...", "...");
//        d3.addTransaction(t3);
//        Donor d4 = new Donor("d4","...", "...");
//        d4.addTransaction(t4);
//
//        Panel p = new Panel();
//        p.addDonor(d1);
//        p.addDonor(d2);
//        p.addDonor(d3);
//        p.addDonor(d4);
//        p.donorOverValue(100);

        UserInput i = new UserInput();
        i.runLoop();

    }
}
