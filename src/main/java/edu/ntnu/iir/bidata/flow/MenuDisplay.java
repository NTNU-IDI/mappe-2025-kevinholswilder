package main.java.edu.ntnu.iir.bidata.flow;

/**
 * Provides helper methods for displaying the main menu and submenus.
 *
 * @Author Kevin Holswilder
 * @Date 2025/11/01
 */

public class MenuDisplay {

    /**
     * Displays the main menu, used in the start flow.
     */
    public static void mainMenu() {
        System.out.println("--=[ Cooking Diary ]=--");
        System.out.println("Welcome to the cooking diary,\nplease select one of the options below.");
        System.out.println("1. Log-in.");
        System.out.println("2. Register a new author.");
        System.out.println("3. Exit the program.");
    }

    /**
     * Displays the cooking diary menu, used in the diary flow.
     */
    public static void diaryMenu() {
        System.out.println("--=[ Cooking Diary ]=--");
        System.out.println("1. Write a new cooking diary entry.");
        System.out.println("2. Delete an existing entry.");
        System.out.println("3. Search through diary entries.");
        System.out.println("4. Export all diary sorted by date.");
        System.out.println("5. Show author statistics.");
        System.out.println("6. Log out.");
    }

    /**
     * Displays the search menu, used in the search flow.
     */
    public static void searchDiaryMenu() {
        System.out.println("--=[ Cooking Diary ]=--");
        System.out.println("1. Search recipe by title.");
        System.out.println("2. Search recipe(s) by date.");
        System.out.println("3. Search recipe(s) by period.");
        System.out.println("4. Search recipe(s) by author.");
        System.out.println("5. Search recipe(s) by prompt.");
        System.out.println("6. Search recipe(s) by label.");
        System.out.println("7. Return back to diary menu.");
    }
}
