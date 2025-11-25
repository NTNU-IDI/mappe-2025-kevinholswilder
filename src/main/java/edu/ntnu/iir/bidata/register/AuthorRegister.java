package main.java.edu.ntnu.iir.bidata.register;

import main.java.edu.ntnu.iir.bidata.models.Author;

import java.util.HashMap;
import java.util.List;

/**
 * Singleton class that ensures there only exists 1 instance of
 * the local database to ensure a single source of truth
 *
 * @Author Kevin Holswilder
 * @Date 21/10/2025
 */

public class AuthorRegister {

    private static AuthorRegister instance;

    private final HashMap<String, Author> authorEntries;

    /**
     * Representation of the database constructor.
     */
    private AuthorRegister() {
        this.authorEntries = new HashMap<>();
    }

    /**
     * @return the single instance of the database.
     */
    static AuthorRegister getInstance() {
        if (instance == null) {
            instance = new AuthorRegister();
        }
        return instance;
    }

    /**
     * @return an [Author] based on the username
     */
    public Author getAuthorByUsername(String username) {
        return this.authorEntries.getOrDefault(username.toLowerCase(), null);
    }

    /**
     * @param author adds an author to the database.
     */
    public void addAuthor(Author author) {
        this.authorEntries.putIfAbsent(author.getUsername().toLowerCase(), author);
    }

    /**
     * @return A list of all authors.
     */
    public List<Author> getAuthors() {
        return this.authorEntries.values().stream().toList();
    }

}
