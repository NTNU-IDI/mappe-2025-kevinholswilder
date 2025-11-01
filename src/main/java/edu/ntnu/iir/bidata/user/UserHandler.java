package main.java.edu.ntnu.iir.bidata.user;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;
import test.java.MainTest;

import java.util.Scanner;

/**
 * Class that handles user authentication and authorization.
 *
 * @Author Kevin Holswilder
 * @Date 25.10.2025
 */

public class UserHandler {

    private static Author currentUser;

    private UserHandler() {}

    public static void login(Scanner input) {
        System.out.println("Please enter your username: ");
        String username = UtilityManager.ensureNonEmptyString(input);

        // Check if the user exists.
        Author author = RegisterHandler.getAuthorDatabase().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Invalid username, you're being sent back to the main menu.");
            return;
        }

        // Login
        System.out.println("Welcome " + author.getName() + "!");

        // Set the current operating user.
        setAuthor(author);
        MainTest.diaryFlow(input);
    }

    public static void register(Scanner input) {
        System.out.println("Please enter your new username: ");
        String username = UtilityManager.ensureNonEmptyString(input);

        // Check if the username exists.
        Author author = RegisterHandler.getAuthorDatabase().getAuthorByUsername(username);
        if (author != null) {
            System.out.println("Username already exists, please try again.");
            return;
        }

        System.out.println("Please enter your name: ");
        String name = UtilityManager.ensureNonEmptyString(input);

        System.out.println("Please enter your surname: ");
        String surname = UtilityManager.ensureNonEmptyString(input);

        // Register the new author.
        Author newAuthor = new Author(username, name, surname);
        RegisterHandler.getAuthorDatabase().addAuthor(newAuthor);

        System.out.println("Thank you for registering, " + newAuthor.getName() + "!");
    }

    public static Author getCurrentUser() {
        return currentUser;
    }

    public static void setAuthor(Author author) {
        currentUser = author;
    }

    public static void logout() {
        currentUser = null;
    }
}
