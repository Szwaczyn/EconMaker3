package tests;

import hoodStuff.DataIntegration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created $(DATE)
 */
class DataIntegrationTest {

    DataIntegration dataIntegration1 = new DataIntegration("450.75");
    DataIntegration dataIntegration2 = new DataIntegration("399.991");
    DataIntegration notIntegrationData = new DataIntegration("W las kotek na plotek");

    @Test
    void isItValidCurrency() {
        assertTrue(dataIntegration1.isItValidCurrency());
        assertFalse(notIntegrationData.isItValidCurrency());
    }

    @Test
    void isItValidCurrency1() {
        assertTrue(dataIntegration1.isItValidCurrency("532.12"));
        assertFalse(dataIntegration1.isItValidCurrency("LegiaPany"));
    }

    @Test
    void getValidCurrency() {
        assertEquals("399.99", dataIntegration2.getValidCurrency());
        assertEquals("0.0", notIntegrationData.getValidCurrency());
    }

    @Test
    void getValidCurrency1() {
        assertEquals("451.51", dataIntegration2.getValidCurrency("451.513"));
        assertEquals("451.0", dataIntegration2.getValidCurrency("451"));
        assertEquals("0.0", notIntegrationData.getValidCurrency("nic specjalnego"));
    }

    @Test
    void isValidDate() {
        assertTrue(dataIntegration1.isValidDate("2015-10-10"));
        assertFalse(dataIntegration1.isValidDate("2010-45-5"));
        assertFalse(dataIntegration1.isValidDate("2010-10-60"));
        assertFalse(dataIntegration1.isValidDate("2854-01-01"));
    }
}