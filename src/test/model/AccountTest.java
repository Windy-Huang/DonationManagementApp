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

//    @Test
//    public void testLoginT() throws EmptyException {
//        assertTrue(a.login("123"));
//    }

//    @Test
//    public void testLoginF() throws EmptyException {
//        assertFalse(a.login("345"));
//    }

//    @Test
//    public void testLoginEx() {
//        assertThrows(EmptyException.class, () -> {
//            a.login("");
//        });
//    }

    @Test
    public void testLoginT() {
        Boolean bool = false;
        try {
            bool = a.login("123");
        } catch (EmptyException e) {
            fail("should not throw exception");
        }
        assertTrue(bool);
    }

    @Test
    public void testLoginF() {
        Boolean bool = true;
        try {
            bool = a.login("345");
        } catch (EmptyException e) {
            fail("should not throw exception");
        }
        assertFalse(bool);
    }

    @Test
    public void testLoginEx() {
        try {
            a.login("");
            fail("should throw exception");
        } catch (EmptyException e) {
        }
    }

}