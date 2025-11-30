package main.java.edu.ntnu.iir.bidata.register;

import main.java.edu.ntnu.iir.bidata.models.Author;

import java.util.HashMap;
import java.util.List;

/**
 * Represents the {@link Author} register.
 *
 * <p>This class is a singleton class that ensures there only exists 1 instance of
 * the local register at a time to ensure a single source of truth throughout the runtime of the program.</p>
 *
 * @Author Kevin Holswilder
 * @Date 2025/10/21
 * @see <a href="https://www.geeksforgeeks.org/java/singleton-class-java/">Singleton Method Design Pattern in Java</a>
 */

public class AuthorRegister {

    private static AuthorRegister instance;

    private final HashMap<String, Author> authorEntries;

    /**
     * Private constructor to prevent instantiation.
     */
    private AuthorRegister() {
        this.authorEntries = new HashMap<>();
    }

    /**
     * @return the single instance of the register.
     */
    static AuthorRegister getInstance() {
        if (instance == null) {
            instance = new AuthorRegister();
        }
        return instance;
    }

    /**
     * @return a {@link Author} where the username matches the query, if no match is found, returns null.
     */
    public Author getAuthorByUsername(String username) {
        return this.authorEntries.getOrDefault(username.toLowerCase(), null);
    }

    /**
     * @param author adds an {@link Author} to the register.
     */
    public void addAuthor(Author author) {
        this.authorEntries.putIfAbsent(author.getUsername().toLowerCase(), author);
    }

    /**
     * @return A list of all {@link Author} objects in the register.
     */
    public List<Author> getAuthors() {
        return this.authorEntries.values().stream().toList();
    }

}
