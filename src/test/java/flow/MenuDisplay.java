package test.java.flow;

/**
 * MenuDisplay class that displays all menu options.
 *
 * @Author Kevin Holswilder
 * @Date 11/01/2025
 */

public class MenuDisplay {

    public static void mainMenu() {
        System.out.println("--=[ Main Menu ]=--");
        System.out.println("Welcome to the diary application,\nplease select one of the options.");
        System.out.println("1. Log-in.");
        System.out.println("2. Register a new author.");
        System.out.println("3. Exit the program.");
    }

    public static void diaryMenu() {
        System.out.println("--=[ Diary Menu ]=--");
        System.out.println("1. Write a new diary entry.");
        System.out.println("2. Delete an existing diary entry.");
        System.out.println("3. Search for diary entries.");
        System.out.println("4. Log out.");
    }

    public static void searchDiaryMenu() {
        System.out.println("--=[ Search Menu ]=--");
        System.out.println("1. Search diary by title.");
        System.out.println("2. Search diaries by date.");
        System.out.println("3. Search diaries by period.");
        System.out.println("4. Search diaries by author.");
        System.out.println("5. Search diary by prompt.");
        System.out.println("6. Return to diary menu.");
    }
}
