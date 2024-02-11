package ui;

import exceptions.ArchieveException;
import exceptions.EmptyException;
import model.Account;
import model.Date;
import model.Donor;
import model.Transaction;

import java.util.Scanner;

// represent an object to handle user input
public class UserInput {

    private final Scanner input;
    private final Panel panel;
    private Boolean state;
    private Donor selectedDonor;
    private Transaction selectedTransaction;

    public UserInput() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        panel = new Panel();
        state = false;
        selectedDonor = new Donor(null, null, null);
        selectedTransaction = new Transaction(null, null, 0);
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
                displayManageDonor();
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
        System.out.println("View all transaction - 2");
        System.out.println("View donor profile - 3");
        System.out.println("Delete this donor - 4");
        System.out.println("Return to home page - any other character");
    }

    // EFFECTS: selecting the donor to edit
    public Boolean handleCurrentDonorSelectDonor() {
        panel.printDonor();
        int i = validInteger();
        if ((i - 1) < panel.getDonors().size()) {
            selectedDonor = panel.getDonors().get(i - 1);
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
                handleViewTransaction();
                break;
            case "3":
                handleViewProfile();
                break;
            case "4":
                handleDeleteDonor();
                break;
        }
    }

    public void handleViewProfile() {
        System.out.println("Name: " + selectedDonor.getName());
        System.out.println("Phone: " + selectedDonor.getPhone());
        System.out.println("Email: " + selectedDonor.getEmail());
        System.out.println("Donation: " + Integer.toString(selectedDonor.getDonation()));
        System.out.println("Transaction:");
        selectedDonor.printTransactions();
    }

    public void handleNewTransaction() {
        System.out.println("You've selected add a new transaction to donor, " + selectedDonor.getName());
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            System.out.println("Please enter the transaction year");
            int year = validInteger();
            System.out.println("Please enter the transaction month");
            int month = validInteger();
            System.out.println("Please enter the transaction day");
            int day = validInteger();
            System.out.println("Please enter the transaction method");
            String type = input.next();
            System.out.println("Please enter the transaction amount");
            int amount = validInteger();
            selectedDonor.addTransaction(new Transaction(new Date(month, day, year), type, amount));
            panel.updateDonation();
        }
    }

    public void handleRemoveTransaction() {
        System.out.println("You've selected remove the transaction");
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            try {
                selectedDonor.removeTransaction(selectedTransaction);
                panel.updateDonation();
                System.out.println("Transaction deleted");
            } catch (ArchieveException e) {
                System.out.println("This transaction is archived, and it cannot be changed");
                System.out.println("Remaining to home page");
            }
        }
    }

    public void handleChangeTransaction() {
        System.out.println("You've selected changed the transaction");
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        if (input.next().equals("p")) {
            System.out.println("Please enter the transaction year");
            int year = validInteger();
            System.out.println("Please enter the transaction month");
            int month = validInteger();
            System.out.println("Please enter the transaction day");
            int day = validInteger();
            System.out.println("Please enter the transaction method");
            String type = input.next();
            System.out.println("Please enter the transaction amount");
            int amount = validInteger();
            try {
                selectedDonor.changeTransaction(selectedTransaction, new Date(month, day, year), type, amount);
                panel.updateDonation();
                System.out.println("Transaction updated");
            } catch (ArchieveException e) {
                System.out.println("This transaction is archived, and it cannot be changed. Returning to home page");
            }
        }
    }

    public void handleArchiveTransaction() {
        System.out.println("You've selected archive the transaction");
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            selectedTransaction.setIsArchive();
            System.out.println("The transaction is archived");
        }
    }

    public void handleViewTransaction() {
        System.out.println("You've selected view all transactions of donor, " + selectedDonor.getName());
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            selectedDonor.printTransactions();
            if (selectedDonor.getTransactions().size() > 0) {
                handleEditTransaction();
            }
        }
    }

    public Boolean handleCurrentTransactionSelectTransaction() {
        int i = validInteger();
        if ((i - 1) < selectedDonor.getTransactions().size()) {
            selectedTransaction = selectedDonor.getTransactions().get(i - 1);
            return false;
        } else {
            System.out.println("Invalid row number, please try again.");
            return true;
        }
    }

    public void handleEditTransaction() {
        System.out.println("From the list of Transaction printed above, "
                + "enter the row number of the transaction you wish to edit");
        Boolean b = true;
        while (b) {
            b = handleCurrentTransactionSelectTransaction();
        }
        System.out.println("Select from the following by entering the number to the right");
        System.out.println("Set a transaction to archive - 1");
        System.out.println("Remove a transaction - 2");
        System.out.println("Change a transaction - 3");
        System.out.println("return to home page - any key");
        String s = input.next();
        switch (s) {
            case "1":
                handleArchiveTransaction();
                break;
            case "2":
                handleRemoveTransaction();
                break;
            case "3":
                handleChangeTransaction();
                break;
        }
    }

    public void handleDeleteDonor() {
        System.out.println("You've selected delete donor, " + selectedDonor.getName());
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            if (notArchiveUser()) {
                System.out.println("Donor deleted");
                panel.removeDonor(selectedDonor);
                panel.updateDonation();
            } else {
                System.out.println("This user contain archived transaction, and it cannot be deleted");
                System.out.println("Returning to home page");
            }
        }
    }

    // EFFECTS: return true if user does not contain archievd transaction
    public Boolean notArchiveUser() {
        for (Transaction t:selectedDonor.getTransactions()) {
            if (t.getIsArchive()) {
                return false;
            }
        }
        return true;
    }

    public void displayManageDonor() {
        System.out.println("You've selected manage all donors");
        System.out.println("If you want to proceed - enter p. To return to home page - enter any key");
        String s = input.next();
        if (s.equals("p")) {
            System.out.println("Select from the following option by entering the number to the right");
            System.out.println("Display all donors with donation above an amount - 1");
            System.out.println("View all donors in descending order by donation made - 2");
            System.out.println("Returning to home page - any key");
            handleManageDonor();
        }
    }

    public void handleManageDonor() {
        String s = input.next();
        switch (s) {
            case "1":
                System.out.println("Only donors with donation over or equal to this value will be displayed");
                int val = validInteger();
                panel.donorOverValue(val);
                break;
            case "2":
                panel.sortDonor();
                panel.printDonor();
                break;
        }
    }

    // EFFECTS: ensure the user only input number
    public int validInteger() {
        String s = input.next();
        while (!s.matches("[0-9]+")) {
            System.out.println("Invalid input, numerical character only");
            s = input.next();
        }
        return (Integer.valueOf(s));
    }
}
