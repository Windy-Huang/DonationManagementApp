package model;

import exceptions.EmptyException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account a;

    @BeforeEach
    public void setUp() {
        a = new Account("hello", "123");
    }

    @Test
    public void testLoginT() throws EmptyException {
        assertTrue(a.login("123"));
    }

    @Test
    public void testLoginF() throws EmptyException {
        assertFalse(a.login("345"));
    }

    @Test
    public void testLoginEx() {
        assertThrows(EmptyException.class, () -> {
            a.login("");
        });
    }

}