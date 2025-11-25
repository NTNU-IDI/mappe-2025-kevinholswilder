package test.java;

import test.java.models.AuthorTest;
import test.java.models.DiaryEntryTest;
import test.java.register.RegisterTest;
import test.java.ui.ConsoleUITest;
import test.java.utils.UtilityManagerTest;

/**
 * Main Class for testing.
 *
 * @Author Kevin Holswilder
 * @Date 27/10/2025
 */

public class MainTest {

    public static void main(String[] args) {
        // Model Test
        AuthorTest authorTest = new AuthorTest();
        authorTest.startAuthorTest();

        DiaryEntryTest diaryEntryTest = new DiaryEntryTest();
        diaryEntryTest.startDiaryEntryTest();

        // Register Test
        RegisterTest registerTest = new RegisterTest();
        registerTest.startRegisterTest();

        // UI Test
        ConsoleUITest consoleUITest = new ConsoleUITest();
        consoleUITest.startUITest();

        // Utility Test
        UtilityManagerTest utilityManagerTest = new UtilityManagerTest();
        utilityManagerTest.startUtilityManagerTest();
    }

}
