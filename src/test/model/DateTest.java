package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    private Date d1;
    private Date d2;
    private Date d3;

    @BeforeEach
    public void setUp() {
        d1 = new Date(1,8,2024);
        d2 = new Date(12,20,2020);
        d3 = new Date(3,10,2010);
    }

    @Test
    public void testShortFormat() {
        assertEquals("01/08/2024", d1.shortFormat());
        assertEquals("12/20/2020", d2.shortFormat());
        assertEquals("03/10/2010", d3.shortFormat());
        assertEquals(3, d3.getMonth());
    }

    @Test
    public void testLongFormat() {
        assertEquals("January 8, 2024", d1.longFormat());
        assertEquals("December 20, 2020", d2.longFormat());
        assertEquals("March 10, 2010", d3.longFormat());
    }

}
