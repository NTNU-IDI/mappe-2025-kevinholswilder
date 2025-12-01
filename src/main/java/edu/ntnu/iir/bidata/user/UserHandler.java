package main.java.edu.ntnu.iir.bidata.user;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.Scanner;

/**
 * This class handles user authentication and authorization within the program.
 *
 * <p>
 *     This class provides static methods allowing users to log in, register, logout and get the current user.
 *     Additionally, it provides helper methods to set and get the current user.
 * </p>
 *
 * @author Kevin Holswilder
 * @since 2025/10/25
 */

public class UserHandler {

    private static Author currentUser;

    /**
     * Private constructor to prevent instantiation.
     */
    private UserHandler() {}

    /**
     * <p>Prompts the user to enter a username, if the username exists, the user is logged in,
     * otherwise the user is redirected to the main menu.</p>
     *
     * @param input a {@link Scanner} object to read user input.
     * @return true if login was successful, false otherwise.
     */
    public static boolean login(Scanner input) {
        System.out.println("Please enter your username: ");
        String username = UtilityManager.ensureNonEmptyTrimmedString(input);

        // Check if the user exists.
        Author author = RegisterHandler.getAuthorRegister().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Could not find user with username: " + username + "\nYou're being sent back to the main menu.");
            return false;
        }

        // Login
        System.out.println("Welcome " + UtilityManager.capitalize(author.getName()) + "!");
        // Set the current user.
        setAuthor(author);
        return true;
    }

    /**
     * <p>Prompts the user to enter a username, name and surname.
     * If the username already exists, the user is redirected to the main menu.
     * In the case that the username is unique, the user will be prompted to enter their name and their surname,
     * which then creates a new {@link Author} which is being added to the register.</p>
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void register(Scanner input) {
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

        // Register the new author.
        Author author = new Author(username, name, surname);
        RegisterHandler.getAuthorRegister().addAuthor(author);

        System.out.println("Thank you for registering, " + author.getName() + "!");
    }

    /**
     * @return a {@link Author} who is currently logged in, or null if no user is logged in.
     */
    public static Author getCurrentUser() {
        return currentUser;
    }

    /**
     * @param author the {@link Author} to set as the current user.
     */
    public static void setAuthor(Author author) {
        currentUser = author;
    }

    /**
     * Logs the current user out by setting the current user to null.
     */
    public static void logout() {
        currentUser = null;
    }
}
