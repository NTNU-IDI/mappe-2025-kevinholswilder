package test.java.edu.ntnu.iir.bidata;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.AuthorRegister;
import main.java.edu.ntnu.iir.bidata.register.DiaryRegister;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.ui.ConsoleUI;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;
import test.java.edu.ntnu.iir.bidata.models.AuthorTest;
import test.java.edu.ntnu.iir.bidata.models.DiaryEntryTest;
import test.java.edu.ntnu.iir.bidata.register.RegisterTest;
import test.java.edu.ntnu.iir.bidata.ui.ConsoleUITest;
import test.java.edu.ntnu.iir.bidata.utils.UtilityManagerTest;

/**
 * Main entry point for running all tests in the project.
 *
 * <p>This class runs the following tests:
 * <ul>
 *     <li>Model Tests ({@link Author}, {@link DiaryEntry})</li>
 *     <li>Register Tests ({@link DiaryEntry})</li>
 *     <li>
 *       Register Tests ({@link RegisterHandler}, {@link DiaryRegister},
 *       {@link AuthorRegister})
 *     </li>
 *     <li>Console UI Test ({@link ConsoleUI})</li>
 *     <li>UtilityManger Test ({@link UtilityManager})</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/10/27
 */

public class MainTest {

  /**
   * Launches all tests.
   *
   * @param args command line arguments.
   */
  @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
  public static void main(String[] args) {
    // Model Test
    AuthorTest authorTest = new AuthorTest();
    authorTest.startAuthorTest();

    DiaryEntryTest diaryEntryTest = new DiaryEntryTest();
    diaryEntryTest.startDiaryEntryTest();

    // Register Test
    RegisterTest registerTest = new RegisterTest();
    registerTest.startRegisterTest();

    // UtilityManager Test
    UtilityManagerTest utilityManagerTest = new UtilityManagerTest();
    utilityManagerTest.startUtilityManagerTest();

    // UI Test
    ConsoleUITest consoleUITest = new ConsoleUITest();
    consoleUITest.startUITest();

    System.out.println("All tests have been run successfully.");
  }

}
