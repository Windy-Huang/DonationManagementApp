package model;

import exceptions.EmptyException;

// represent an account with a username and a password
public class Account {

    private final String username;
    private final String password;

    // EFFECTS: create an object with a username and password
    public Account(String user, String pass) {
        username = user;
        password = pass;
    }

    // EFFECTS: return true if the entered password, pass, matches the password of username
    //          if entered password is empty, throws emptyException
    public Boolean login(String pass) throws EmptyException {
        if (pass.isEmpty()) {
            throw new EmptyException();
        }
        return (pass.equals(password));
    }

}
