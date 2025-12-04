package main.java.edu.ntnu.iir.bidata.ui.user;

import java.util.Scanner;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.service.UserService;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

/**
 * This class provides methods for prompting the user for login and registration.
 * Additionally, this class works together with the {@link UserService} class to
 * perform the actual login and registration.
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UserUI {

  private static final UserService userService = new UserService();

  /**
   * Prompts the user to enter a username and logs them in if the username exists.
   *
   * @param input a {@link Scanner} object to read user input.
   * @return true if the login was successful, false otherwise.
   */
  public boolean promptLogin(Scanner input) {
    System.out.println("Please enter your username: ");
    String username = UtilityManager.ensureNonEmptyTrimmedString(input);

    boolean isLoginSuccessful = userService.login(username);
    if (isLoginSuccessful) {
      System.out.println("Welcome " + UserService.getCurrentUser().getName() + "!");
      return true;
    } else {
      System.out.println("Could not find user with username " + username + ", please try again.");
      return false;
    }
  }

  /**
   * Prompts the user to enter a username, name and surname
   * and registers them if the username does not exist.
   *
   * @param input a {@link Scanner} object to read user input.
   */
  public void promptRegister(Scanner input) {
    System.out.println("Please enter an username: ");
    String username = UtilityManager.ensureOnlyAlphabeticLetters(input);

    // Check if the username already exists.
    Author potentialAuthor = RegisterHandler.getAuthorRegister().getAuthorByUsername(username);
    if (potentialAuthor != null) {
      System.out.println("Username already exists, you're being sent back to the main menu.");
      return;
    }

    System.out.println("Please enter your name: ");
    String name = UtilityManager.ensureNonEmptyTrimmedString(input);

    System.out.println("Please enter your surname: ");
    String surname = UtilityManager.ensureNonEmptyTrimmedString(input);

    userService.register(username, name, surname);
  }

  /**
   * Logs the user out.
   */
  public void logout() {
    userService.logout();
    System.out.println("You have been logged out.");
  }

}
