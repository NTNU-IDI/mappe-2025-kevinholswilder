package main.java.edu.ntnu.iir.bidata.service;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.register.AuthorRegister;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;

/**
 * <p>
 *     This class provides methods for user authentication and registration.
 *     Additionally, it provides helper-methods for accessing the current logged-in user.
 * </p>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class UserService {

    private static Author currentUser;

    /**
     * Checks if a username is linked to a {@link Author} object in the register and logs the user in if it is.
     *
     * @param username the username to check.
     * @return true if the {@link Author} exists, false otherwise.
     */
    public boolean login(String username) {
        Author author = RegisterHandler.getAuthorRegister().getAuthorByUsername(username);
        if (author == null) {
            return false;
        }
        setCurrentUser(author);
        return true;
    }

    /**
     * Registers a new {@link Author} object in the {@link AuthorRegister}.
     *
     * @param username the username of the author.
     * @param name the name of the author.
     * @param surname the surname of the author.
     */
    public void register(String username, String name, String surname) {
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
    public void setCurrentUser(Author author) {
        currentUser = author;
    }

    /**
     * Logs the current user out by setting the current user to null.
     */
    public void logout() {
        setCurrentUser(null);
    }

}
