package ui;

import exceptions.EmptyException;
import model.Account;
import model.Date;
import model.Donor;
import model.Transaction;

import java.util.Scanner;

// represent an object to handle user input
public class UserInput {

    private Scanner input;
    private Panel panel;
    private Boolean state;
    private Donor selected;

    public UserInput() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        panel = new Panel();
        state = false;
        selected = new Donor(null, null, null);
    }

    // EFFECTS: continues the application until the user chooses to quit
    public void runLoop() {

        Account acc = new Account("RMCS", "123");

        while (!state) {
            state = displayAccount(acc);
        }

        while (state) {
            displayMain();
            handleKeyMain();
        }

    }

    // EFFECTS: display the login option that user can choose
    //          return true if user login is successful
    public Boolean displayAccount(Account a) {
        System.out.println("Welcome to the donation database user RMCS, please enter your password");
        String s1 = input.next();
        try {
            if (a.login(s1)) {
                System.out.println("You are logged in, welcome user RMCS");
                return true;
            } else {
                System.out.println("Password is not correct, please try again");
                return false;
            }
        } catch (EmptyException e) {
            System.err.println("the password is empty, please try again");
            return false;
        }
    }

    // EFFECTS: display the main panel that user can choose
    public void displayMain() {
        System.out.println("RMCS currently have " + panel.getDonors().size() + " donors with a total donation of "
                + panel.getTotalDonation() + " dollars.");
        System.out.println("Select from the following by entering the number right of the choice");
        System.out.println("Add a new donor - 1");
        System.out.println("Edit a current donor - 2");
        System.out.println("Manage all donors - 3");
        System.out.println("Logout - 4");
    }

    // EFFECTS; handle user key input at the main page
    public void handleKeyMain() {
        String s = input.next();
        switch (s) {
            case "1":
                handleNewDonor();
                break;
            case "2":
                if (panel.getDonors().isEmpty()) {
                    System.out.println("Invalid action, there is no donor in the database");
                } else {
                    displayCurrentDonor();
                    handleCurrentDonor();
                }
                break;
            case "3":
                handleMangeDonor();
                break;
            case "4":
                System.out.println("See you next time user RMCS");
                state = false;
                break;
            default:
                System.out.println("The option that you selected is currently unavailable");
        }
    }

    public void handleNewDonor() {
        System.out.println("You've selected add a new donor");
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            System.out.println("Please enter the donor name");
            String name = input.next();
            System.out.println("Please enter the donor email");
            String email = input.next();
            System.out.println("Please enter the donor phone");
            String phone = input.next();
            panel.addDonor(new Donor(name, email, phone));
        }
    }

    public void displayCurrentDonor() {
        System.out.println("You've selected Edit a current donor");
        System.out.println("From the list of current donors printed below, "
                + "enter the row number of the donor you wish to edit");
        Boolean b = true;
        while (b) {
            b = handleCurrentDonorSelectDonor();
        }
        System.out.println("Select from the following by entering the number to the right");
        System.out.println("Add a new transaction - 1");
        System.out.println("Remove a current transaction - 2");
        System.out.println("View all transaction - 3");
        System.out.println("Return to home page - any other character");
    }

    // EFFECTS: selecting the donor to edit
    public Boolean handleCurrentDonorSelectDonor() {
        panel.printDonor();
        Integer i = Integer.valueOf(input.next());
        if ((i - 1) < panel.getDonors().size()) {
            selected = panel.getDonors().get(i - 1);
            return false;
        } else {
            System.out.println("Invalid row number, please try again.");
            return true;
        }
    }

    // EFFECTS: handle operation to be performed on the selected donor
    public void handleCurrentDonor() {
        String s = input.next();
        switch (s) {
            case "1":
                handleNewTransaction();
                break;
            case "2":
                handleRemoveTransaction();
                break;
            case "3":
                handleViewTransaction();
                break;
        }
    }

    public void handleNewTransaction() {
        System.out.println("You've selected add a new transaction to donor, " + selected.getName());
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            System.out.println("Please enter the transaction year");
            int year = Integer.valueOf(input.next());
            System.out.println("Please enter the transaction month");
            int month = Integer.valueOf(input.next());
            System.out.println("Please enter the transaction day");
            int day = Integer.valueOf(input.next());
            System.out.println("Please enter the transaction method");
            String type = input.next();
            System.out.println("Please enter the transaction amount");
            int amount = Integer.valueOf(input.next());
            selected.addTransaction(new Transaction(new Date(month, day, year), type, amount));
            panel.updateDonation();
        }
    }

    public void handleRemoveTransaction() {

    }

    public void handleViewTransaction() {

    }

    public void handleMangeDonor() {

    }
}
