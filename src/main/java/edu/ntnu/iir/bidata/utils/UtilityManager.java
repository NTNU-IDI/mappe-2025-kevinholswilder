package main.java.edu.ntnu.iir.bidata.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                System.out.println("Please make sure that the date you entered is valid (Example: yyyy-MM-dd).");
            }
        }
    }
}
