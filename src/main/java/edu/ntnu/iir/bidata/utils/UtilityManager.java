package main.java.edu.ntnu.iir.bidata.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides static utility methods for validating user input.
 *
 * @author Kevin Holswilder
 * @since 2025/10/25
 */

public class UtilityManager {

  /**
   * Private constructor to prevent instantiation.
   */
  private UtilityManager() {
  }

  /**
   * Ensures that the user enters a non-empty trimmed string.
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a non-empty trimmed string.
   */
  public static String ensureNonEmptyTrimmedString(Scanner input) {
    while (true) {
      String string = input.nextLine();
      if (string.isEmpty()) {
        System.out.println("Please make sure that the value you entered is not empty.");
      } else {
        return string.trim();
      }
    }
  }

  /**
   * Ensures that the user enters a valid integer.
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a validated integer.
   */
  public static int ensureValidInteger(Scanner input) {
    while (true) {
      String string = input.nextLine();
      try {
        return Integer.parseInt(string);
      } catch (Exception e) {
        System.out.println("Please make sure that the value you entered is a valid integer.");
      }
    }
  }

  /**
   * Prompts the user to enter a date in the format YYYY-MM-DD.
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a {@link LocalDate} object.
   */
  public static LocalDate ensureValidDate(Scanner input) {
    while (true) {
      String string = input.nextLine();
      try {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      } catch (Exception e) {
        System.out.println(
            "Please make sure that the date you entered is valid (Format: YYYY-MM-DD).");
      }
    }
  }

  /**
   * Capitalizes the first letter of a string.
   *
   * @param string the string to be capitalized.
   * @return the capitalized string.
   */
  public static String capitalize(String string) {
    return string.substring(0, 1).toUpperCase() + string.substring(1);
  }

  /**
   * Reads all lines from a {@link Scanner} until the user enters "exit".
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a string consisting of all the lines read from the scanner.
   */
  public static String readMultiLineInput(Scanner input) {
    StringBuilder stringBuilder = new StringBuilder();
    while (input.hasNextLine()) {
      String line = input.nextLine();
      if (line.equalsIgnoreCase("exit")) {
        break;
      }
      stringBuilder.append(line).append("\n");
    }
    return stringBuilder.toString();
  }

  /**
   * Reads all lines from a {@link Scanner} until the user enters "exit".
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a list of strings containing all the lines read from the {@link Scanner}.
   */
  public static List<String> readMultiLineInputList(Scanner input) {
    return List.of(readMultiLineInput(input).split("\n"));
  }

  /**
   * This method ensures that the user enters only alphabetic letters,
   * which prevents users from entering special characters, or white space.
   *
   * @param input a {@link Scanner} object to read user input.
   * @return a string consisting of only alphabetic letters.
   */
  public static String ensureOnlyAlphabeticLetters(Scanner input) {
    while (true) {
      String string = input.nextLine();
      if (!string.isEmpty() && string.chars().allMatch(Character::isAlphabetic)) {
        return string;
      } else {
        System.out.println(
            """
                Please make sure that the value you enter only consists out of alphabetic letters.
                For example: 'johndoe' or 'janedoe'.
                Your username cannot contain any white space or special characters."""
        );
      }
    }
  }

}
