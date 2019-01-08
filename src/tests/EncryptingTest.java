package tests;

import builder.EncryptBuilder;
import hoodStuff.Encrypting;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created $(DATE)
 */
class EncryptingTest {

    Encrypting encrypt = new EncryptBuilder()
            .addContent("Test12345")
            .build();

    Encrypting encrypt2 = new EncryptBuilder()
            .addContent("Test54321")
            .build();

    @org.junit.jupiter.api.Test
    void MD5() {
        assertNotEquals(encrypt.MD5(), encrypt2.MD5());
    }

    @org.junit.jupiter.api.Test
    void MD51() {
        assertEquals(encrypt.MD5("test5432112345"), encrypt2.MD5("test5432112345"));
        assertNotEquals(encrypt.MD5("test5432112345"), encrypt2.MD5("Test5432112345"));
    }
}