package com.hendisantika.usermanagement.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testAdditionWithZero() {
        assertEquals(5, calculator.add(5, 0));
    }

    @Test
    void testDivision() {
        assertEquals(4, calculator.divide(8, 2));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(10, 0));
    }
}
