package main.java.edu.ntnu.iir.bidata.utils;

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
}
