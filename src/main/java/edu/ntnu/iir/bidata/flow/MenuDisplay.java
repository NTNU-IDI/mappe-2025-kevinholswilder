package main.java.edu.ntnu.iir.bidata.flow;

import main.java.edu.ntnu.iir.bidata.ui.InteractionKeys;
import main.java.edu.ntnu.iir.bidata.service.UserService;

/**
 * Provides helper methods for displaying the main menu and submenus.
 *
 * @author Kevin Holswilder
 * @since 2025/11/01
 */

public class MenuDisplay {

    /**
     * Displays the main menu, used in the start flow.
     */
    public static void mainMenu() {
        printHeader();
        System.out.println("Welcome to the cooking diary,\nplease select one of the options below.");
        System.out.println(InteractionKeys.LOGIN + ". Log-in.");
        System.out.println(InteractionKeys.REGISTER + ". Register a new author.");
        System.out.println(InteractionKeys.EXIT_PROGRAM + ". Exit the program.");
    }

    /**
     * Displays the cooking diary menu, used in the diary flow.
     */
    public static void diaryMenu() {
        printHeader();
        System.out.println(InteractionKeys.WRITE_DIARY + ". Write a new cooking diary entry.");
        System.out.println(InteractionKeys.DELETE_DIARY + ". Delete an existing entry.");
        System.out.println(InteractionKeys.RUN_EDIT_FLOW + ". Edit an existing entry.");
        System.out.println(InteractionKeys.RUN_SEARCH_FLOW + ". Search through diary entries.");
        System.out.println(InteractionKeys.EXPORT_DIARIES + ". Export all diary sorted by date.");
        System.out.println(InteractionKeys.EXPORT_AUTHOR_STATISTICS + ". Show author statistics.");
        System.out.println(InteractionKeys.LOGOUT + ". Log out.");
    }

    /**
     * Displays the editing menu, used in the edit flow.
     */
    public static void editDiaryMenu() {
        printHeader();
        System.out.println(InteractionKeys.EDIT_ADD_LABEL + ". Add a new label.");
        System.out.println(InteractionKeys.EDIT_REMOVE_LABEL + ". Remove an existing label.");
        System.out.println(InteractionKeys.EDIT_CONTENT_LINE + ". Change the content of an existing recipe.");
        System.out.println(InteractionKeys.EDIT_RETURN + ". Return back to diary menu.");
    }

    /**
     * Displays the search menu, used in the search flow.
     */
    public static void searchDiaryMenu() {
        printHeader();
        System.out.println(InteractionKeys.SEARCH_TITLE + ". Search recipe by title.");
        System.out.println(InteractionKeys.SEARCH_DATE + ". Search recipe(s) by date.");
        System.out.println(InteractionKeys.SEARCH_PERIOD + ". Search recipe(s) by period.");
        System.out.println(InteractionKeys.SEARCH_AUTHOR + ". Search recipe(s) by author.");
        System.out.println(InteractionKeys.SEARCH_PROMPT + ". Search recipe(s) by prompt.");
        System.out.println(InteractionKeys.SEARCH_LABEL + ". Search recipe(s) by label.");
        System.out.println(InteractionKeys.SEARCH_RETURN + ". Return back to diary menu.");
    }

    /**
     * Prints the header of the program.
     */
    private static void printHeader() {
        String username = UserService.getCurrentUser() != null ? UserService.getCurrentUser().toString() : "None";
        System.out.println(" ");
        System.out.println("=== Cooking Diary (User: " + username + ") ===");
    }

}
