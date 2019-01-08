package tests;

import builder.EncryptBuilder;
import builder.UserDataBuilder;
import hoodStuff.Encrypting;
import hoodStuff.UserData;
import hoodStuff.UserFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Użytkownik Testowany ms
 * Musi znajdować się w bazie danych z id 9 oraz hasłem ms
 * Użytkownik userbag nie powinien się znajdować w bazie danych
 * Pytanie pomocnicze to q
 * Odpowiedz na pytanie pomocnicze to ms
 */
class UserDataTest {

    UserData user = new UserDataBuilder()
            .addUser("ms")
            .build();

    UserData userBag = new UserDataBuilder()
            .addUser("userBag")
            .build();

    Encrypting encrypt = new EncryptBuilder()
            .build();

    @Test
    void getFileNameBoudget() {
        assertEquals("boudgetms.dll", user.getFileNameBoudget());
        assertEquals("boudgetuserBag.dll", userBag.getFileNameBoudget());
    }

    @Test
    void getProfilPath() {
        assertEquals("src/settings/profiles/ms/", user.getProfilePath());
        assertEquals("src/settings/profiles/userBag/", userBag.getProfilePath());
    }

    @Test
    void getPassword() {
        user.setUser(user.getLogin());
        String password = user.getPassword();
        String validPassword = encrypt.MD5("ms");
        String notValidPassword = encrypt.MD5("Ms");

        assertEquals(password, validPassword);
        assertNotEquals(notValidPassword, password);
    }

    @Test
    void getLineOfLogin() {
        assertEquals(9, user.getLineOfLogin());
        assertEquals(-1, userBag.getLineOfLogin());
    }

    @Test
    void getLineOfLogin1() {
        assertEquals(9, user.getLineOfLogin("ms"));
        assertEquals(-1, userBag.getLineOfLogin("userBag"));
    }

    @Test
    void getPassword1() {
        user.setUser(user.getLogin());
        String password = user.getPassword();
        String validPassword = encrypt.MD5("ms");
        String notValidPassword = encrypt.MD5("Ms");

        assertEquals(password, validPassword);
        assertNotEquals(notValidPassword, password);
    }

    @Test
    void getQuestion() {
        String question = user.getQuestion(user.getLoginPosition()).trim();

        assertEquals("q", question);
    }

    @Test
    void getAnswer() {
        String answer = user.getAnswer();

        assertEquals(encrypt.MD5("ms"), answer);
    }

    @Test
    void getAnswer1() {
        String answer = user.getAnswer(user.getLoginPosition());

        assertEquals(encrypt.MD5("ms"), answer);
    }

    @Test
    void getLoginPosition() {
        int loginPosition = user.getLoginPosition();

        assertEquals(9, loginPosition);
    }

    @Test
    void getLogin() {
        String login = user.getLogin();

        assertEquals("ms", login);
    }

    @Test
    void checkPassword() {
        String encryptedPassword = encrypt.MD5("ms");
        String password = user.getPassword();

        assertEquals(encryptedPassword, password);
    }

    @Test
    void getFileNameCategories() {
        assertEquals("categoriesms.dll", user.getFileNameCategories());
        assertEquals("categoriesuserBag.dll", userBag.getFileNameCategories());
    }

    @Test
    void getProfilePath() {
        assertEquals("src/settings/profiles/ms/", user.getProfilePath());
        assertEquals("src/settings/profiles/userBag/", userBag.getProfilePath());
    }

    @Test
    void getFileNameAccount() {
        assertEquals("BASEalior.base", user.getFileNameAccount("alior"));
        assertEquals("BASEbgz.base", userBag.getFileNameAccount("bgz"));
        assertNotEquals("BASEalior", user.getFileNameAccount("alior"));
        assertNotEquals("BASEalior.BasE", userBag.getFileNameAccount("alior"));
    }

    @Test
    void getFileNameProfile() {
        assertEquals("ms.dll", user.getFileNameProfile());
        assertEquals("userBag.dll", userBag.getFileNameProfile());
    }
}