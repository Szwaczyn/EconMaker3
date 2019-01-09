package tests;

import hoodStuff.LanguageEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created $(DATE)
 */
class LanguageEngineTest {

    LanguageEngine translation = new LanguageEngine();

    @Test
    void setUpLanguage() {
        translation.changeLanguagePack("English");
        assertEquals("Address of file", translation.setUpLanguage(17));
        assertNotEquals("Dodano wydatek", translation.setUpLanguage(1));

        translation.changeLanguagePack("Polski");
        assertEquals("Dodano wydatek", translation.setUpLanguage(112));
        assertEquals("Nowe hasło", translation.setUpLanguage(73));

    }

    @Test
    void changeLanguagePack() {
        translation.changeLanguagePack("English");
        assertEquals("Database", translation.setUpLanguage(8));

        translation.changeLanguagePack("Polski");
        assertEquals("Baza danych", translation.setUpLanguage(8));
    }
}