package main.java.edu.ntnu.iir.bidata.flow;

import java.util.Scanner;
import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.ui.InteractionKeys;
import main.java.edu.ntnu.iir.bidata.ui.diary.DiaryCreateUI;
import main.java.edu.ntnu.iir.bidata.ui.diary.DiaryDeleteUI;
import main.java.edu.ntnu.iir.bidata.ui.diary.DiaryEditUI;
import main.java.edu.ntnu.iir.bidata.ui.diary.DiaryExportUI;
import main.java.edu.ntnu.iir.bidata.ui.diary.DiarySearchUI;
import main.java.edu.ntnu.iir.bidata.ui.user.UserUI;

/**
 * Provides methods for the flow of the program.
 *
 * <p>This class is responsible for handling the user input
 * and redirecting the user to the correct action.</p>
 *
 * @author Kevin Holswilder
 * @since 2025/11/01
 */

public class FlowHandler {

  /**
   * UI objects for the diary flow.
   */
  private static final DiaryCreateUI diaryCreateUI = new DiaryCreateUI();
  private static final DiaryDeleteUI diaryDeleteUI = new DiaryDeleteUI();
  private static final DiaryEditUI diaryEditUI = new DiaryEditUI();
  private static final DiaryExportUI diaryExportUI = new DiaryExportUI();
  private static final DiarySearchUI diarySearchUI = new DiarySearchUI();
  private static final UserUI userUI = new UserUI();

  /**
   * Private constructor to prevent instantiation.
   */
  private FlowHandler() {
  }

  /**
   * Runs the main flow of the program.
   *
   * <p>This method is responsible for processing the user input
   * and performing the following actions:</p>
   * <ul>
   *     <li>Logging in</li>
   *     <li>Registering a new {@link Author}</li>
   *     <li>Exiting the program</li>
   * </ul>
   *
   * @param input a {@link Scanner} object to read user input.
   */
  public static void startFlow(Scanner input) {
    int option = InteractionKeys.INITIAL_VALUE;
    while (option != InteractionKeys.EXIT_PROGRAM) {
      MenuDisplay.mainMenu();

      try {
        option = Integer.parseInt(input.nextLine());
      } catch (Exception e) {
        System.out.println("Invalid input, please try again.");
        continue;
      }

      switch (option) {
        case InteractionKeys.LOGIN -> {
          boolean isLoggedIn = userUI.promptLogin(input);
          if (isLoggedIn) {
            diaryFlow(input);
          }
        }
        case InteractionKeys.REGISTER -> userUI.promptRegister(input);
        case InteractionKeys.EXIT_PROGRAM -> System.out.println("Exiting program..");
        default -> System.out.println("Invalid option, please try again.");
      }

    }
  }

  /**
   * Runs the diary flow of the program.
   *
   * <p>This method is responsible for processing the user input
   * and performing the following actions:</p>
   * <ul>
   *     <li>Write a new {@link DiaryEntry}.</li>
   *     <li>Delete an existing {@link DiaryEntry}.</li>
   *     <li>Run the search flow of the program.</li>
   *     <li>List all {@link DiaryEntry} objects in the register.</li>
   *     <li>List all {@link Author} objects with the {@link DiaryEntry} count they've written.</li>
   *     <li>Log out and return to the main flow of the program.</li>
   * </ul>
   *
   * @param input a {@link Scanner} object to read user input.
   */
  private static void diaryFlow(Scanner input) {
    int option = InteractionKeys.INITIAL_VALUE;
    while (option != InteractionKeys.LOGOUT) {
      MenuDisplay.diaryMenu();

      try {
        option = Integer.parseInt(input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid option, please try again.");
        continue;
      }

      switch (option) {
        case InteractionKeys.WRITE_DIARY -> diaryCreateUI.writeDiary(input);
        case InteractionKeys.DELETE_DIARY -> diaryDeleteUI.deleteDiary(input);
        case InteractionKeys.RUN_EDIT_FLOW -> editDiaryFlow(input);
        case InteractionKeys.RUN_SEARCH_FLOW -> searchDiaryFlow(input);
        case InteractionKeys.EXPORT_DIARIES -> diaryExportUI.exportDiaries();
        case InteractionKeys.EXPORT_AUTHOR_STATISTICS -> diaryExportUI.exportAuthorStatistics();
        case InteractionKeys.LOGOUT -> userUI.logout();
        default -> System.out.println("Invalid option, please try again.");
      }
    }
  }

  /**
   * Runs the edit flow of the program.
   *
   * <p>This method is responsible for processing the user input
   * and performing the following actions:</p>
   * <ul>
   *     <li>Add a new {@link RecipeLabel} to an existing {@link DiaryEntry}.</li>
   *     <li>Remove an existing {@link RecipeLabel} from a {@link DiaryEntry}.</li>
   *     <li>Edit individual lines of a {@link DiaryEntry} its content.</li>
   *     <li>Return to the main flow of the program.</li>
   * </ul>
   */
  private static void editDiaryFlow(Scanner input) {
    int option = InteractionKeys.INITIAL_VALUE;
    while (option != InteractionKeys.EDIT_RETURN) {
      MenuDisplay.editDiaryMenu();

      try {
        option = Integer.parseInt(input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid option, please try again.");
        continue;
      }

      switch (option) {
        case InteractionKeys.EDIT_ADD_LABEL -> diaryEditUI.addLabelToDiary(input);
        case InteractionKeys.EDIT_REMOVE_LABEL -> diaryEditUI.removeLabelFromDiary(input);
        case InteractionKeys.EDIT_CONTENT_LINE -> diaryEditUI.editContentLine(input);
        case InteractionKeys.EDIT_RETURN -> System.out.println("Returning to diary menu...");
        default -> System.out.println("Invalid option, please try again.");
      }
    }
  }

  /**
   * Runs the search flow of the program.
   *
   * <p>This method is responsible for processing the user input
   * and performing the following actions:</p>
   * <ul>
   *     <li>List all {@link DiaryEntry} where the title matches the query.</li>
   *     <li>List all {@link DiaryEntry} where the date matches the query.</li>
   *     <li>List all {@link DiaryEntry} between a period of dates.</li>
   *     <li>List all {@link DiaryEntry} where the {@link Author} matches the username.</li>
   *     <li>List all {@link DiaryEntry} where the content contains the query.</li>
   *     <li>List all {@link DiaryEntry} whose labels contain the query.</li>
   *     <li>Return to the main flow of the program.</li>
   * </ul>
   *
   * @param input a {@link Scanner} object to read user input.
   */
  private static void searchDiaryFlow(Scanner input) {
    int option = InteractionKeys.INITIAL_VALUE;
    while (option != InteractionKeys.SEARCH_RETURN) {
      MenuDisplay.searchDiaryMenu();

      try {
        option = Integer.parseInt(input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid option, please try again.");
        continue;
      }

      switch (option) {
        case InteractionKeys.SEARCH_TITLE -> diarySearchUI.searchDiaryByTitle(input);
        case InteractionKeys.SEARCH_DATE -> diarySearchUI.searchDiaryByDate(input);
        case InteractionKeys.SEARCH_PERIOD -> diarySearchUI.searchDiaryBetweenPeriod(input);
        case InteractionKeys.SEARCH_AUTHOR -> diarySearchUI.searchDiaryByAuthor(input);
        case InteractionKeys.SEARCH_PROMPT -> diarySearchUI.searchDiaryByPrompt(input);
        case InteractionKeys.SEARCH_LABEL -> diarySearchUI.searchDiaryByLabel(input);
        case InteractionKeys.SEARCH_RETURN -> System.out.println("Returning to diary menu...");
        default -> System.out.println("Invalid option, please try again.");
      }
    }
  }

}
