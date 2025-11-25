package main.java.edu.ntnu.iir.bidata.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * UtilityManager that contains helper methods.
 *
 * @Author Kevin Holswilder
 * @Date 25/10/2025
 */

public class UtilityManager {

    /**
     * @param input Scanner object.
     * @return a non-empty string.
     */
    public static String ensureNonEmptyString(Scanner input) {
        while (true) {
            String string = input.nextLine();
            if (string.isEmpty()) {
                System.out.println("Please make sure that the value you entered is not empty.");
            } else {
                return string;
            }
        }
    }

    /**
     * @param input Scanner object.
     * @return an object of [LocalDate].
     */
    public static LocalDate ensureValidDate(Scanner input) {
        while (true) {
            String string = input.nextLine();
            try {
                return LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                System.out.println("Please make sure that the date you entered is valid (Example: YYYY-MM-DD).");
            }
        }
    }

    /**
     * @param string the string to be capitalized.
     * @return the capitalized string.
     */
    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * @return a string consisting of all the lines read from the scanner.
     */
    public static String readMultiLineInput(Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * @return a list of strings containing all the lines read from the scanner.
     */
    public static List<String> readMultiLineInputList(Scanner scanner) {
        return List.of(readMultiLineInput(scanner).split("\n"));
    }

    /**
     * @return a string consisting of only alphabetic letters.
     */
    public static String ensureOnlyAlphabeticLetters(Scanner scanner) {
        while (true) {
            String string = scanner.nextLine();
            if (!string.isEmpty() && string.chars().allMatch(Character::isAlphabetic)) {
                return string;
            } else {
                System.out.println("Please make sure that the value you enter only consists out of alphabetic letters.\nFor example: 'johndoe' or 'janedoe'.\nYour username cannot contain any white space or special characters.");
            }
        }
    }

}
