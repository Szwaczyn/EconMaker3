package tests;

import builder.UserFileBuilder;
import hoodStuff.UserFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created $(DATE)
 */
class UserFileTest {

    UserFile file = new UserFileBuilder()
            .addPath("src/tests/")
            .addFileName("testFile.txt")
            .build();

    UserFile fileNotExist = new UserFileBuilder()
            .addPath("src/tests/")
            .build();

    @Test
    void writeDown() {
        file.createFile();

        int lines = file.size();

        file.writeDown("test");

        assertEquals(lines + 1, file.size());
        file.deleteFile();
    }

    @Test
    void createFile() {
        file.createFile();
        assertTrue(file.isExist());

        fileNotExist.createFile();
        assertFalse(fileNotExist.isExist());
        file.deleteFile();
    }

    @Test
    void show() {
        assertEquals("testFile.txt" + "  " + "src/tests/", file.show());
    }

    @Test
    void readLine() {
        file.createFile();
        file.writeDown("test1");
        file.writeDown("test2");
        file.writeDown("test3");
        file.writeDown("");
        file.writeDown("Linia do odczytu");

        assertEquals("Linia do odczytu", file.readLine(5));
        file.deleteFile();
    }

    @Test
    void size() {
        file.createFile();
        file.writeDown("RedHat");
        file.writeDown("true");
        file.writeDown("Debian");

        assertEquals(3, file.size());
        file.deleteFile();
    }

    @Test
    void removeLine() {
        file.createFile();

        file.writeDown("ok");
        file.writeDown("ok");
        file.writeDown("ok");

        int oldSize = file.size();

        file.removeLine(0);

        int newSize = file.size();

        assertEquals(oldSize, newSize);

        file.deleteFile();
    }

    @Test
    void searchLine() {
        file.createFile();

        file.writeDown("test1");
        file.writeDown("test2");
        file.writeDown("test3");
        file.writeDown("test4");
        file.writeDown("test5");
        file.writeDown("test6");

        assertEquals(4, file.searchLine("test4"));
        assertEquals(-1, file.searchLine("test7"));

        file.deleteFile();
    }

    @Test
    void changeLine() {
        file.createFile();

        file.writeDown("test1");
        file.writeDown("test4");
        file.writeDown("test3");

        file.changeLine("test2",1);

        assertEquals("test2", file.readLine(2));

        file.deleteFile();
    }

    @Test
    void deleteDirectory() {
        try {
            Files.createDirectory(Paths.get("src/tests/testDirectory"));
        } catch (IOException e) {
            assertNull(e);
        }

        File file1 = new File("src/tests/testDirectory");

        assertTrue(file1.isDirectory());
        assertTrue(file1.exists());

        file.deleteDirectory(file1);

        assertFalse(file1.exists());
    }

    @Test
    void isExist() {
        assertFalse(fileNotExist.isExist());

        file.createFile();

        assertTrue(file.isExist());

        file.deleteFile();
    }
}