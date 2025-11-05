package main.java.edu.ntnu.iir.bidata.flow;

import main.java.edu.ntnu.iir.bidata.diary.DiaryHandler;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;

import java.util.Scanner;

/**
 * FlowHandler class that handles the main flow of the program.
 *
 * @Author Kevin Holswilder
 * @Date 01/11/2025
 */

public class FlowHandler {

    private FlowHandler() {}

    /**
     * Starts the main flow of the program.
     * @param input Scanner object.
     */
    public static void startFlow(Scanner input) {
        int option = -1;
        while (option != 3) {
            MenuDisplay.mainMenu();

            try {
                option = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }

            switch (option) {
                case 1 -> {
                    boolean isLoggedIn = UserHandler.login(input);
                    if (isLoggedIn) {
                        FlowHandler.diaryFlow(input);
                    }
                }
                case 2 -> UserHandler.register(input);
                case 3 -> System.out.println("Exiting program..");
            }

        }
    }

    /**
     * Handles the diary flow of the program.
     * @param input Scanner object.
     */
    public static void diaryFlow(Scanner input) {
        int option = -1;
        while (option != 4) {
            MenuDisplay.diaryMenu();

            try {
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option, please try again.");
            }

            switch (option) {
                case 1 -> DiaryHandler.writeDiary(input);
                case 2 -> DiaryHandler.deleteDiary(input);
                case 3 -> FlowHandler.searchDiaryFlow(input);
                case 4 -> UserHandler.logout();
            }
        }
    }

    /**
     * Handles the search flow of the program.
     * @param input Scanner object.
     */
    public static void searchDiaryFlow(Scanner input) {
        int option = -1;
        while (option != 6) {
            MenuDisplay.searchDiaryMenu();

            try {
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option, please try again.");
            }

            switch (option) {
                case 1 -> {
                    // TODO - Implement functionality for searching diaries.
                }
                case 6 -> System.out.println("Returning to diary menu...");
            }
        }
    }

}
