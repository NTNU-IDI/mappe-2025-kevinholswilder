package main.java.edu.ntnu.iir.bidata.database;

import main.java.edu.ntnu.iir.bidata.models.Author;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Singleton class that ensures there only exists 1 instance of
 * the local database to ensure a single source of truth
 *
 * @Author Kevin Holswilder
 * @Date 21/10/2025
 */

public class AuthorDatabase {

    private static AuthorDatabase instance;

    private final HashMap<UUID, Author> authorEntries;

    /**
     * Representation of the database constructor.
     */
    private AuthorDatabase() {
        try {
            this.authorEntries = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException("Could not establish database.", e.getCause());
        }
    }

    /**
     * @return the single instance of the database.
     */
    public static AuthorDatabase getInstance() {
        if (instance == null) {
            instance = new AuthorDatabase();
        }
        return instance;
    }

    /**
     * @param author adds an author to the database.
     */
    public void addAuthor(Author author) {
        this.authorEntries.putIfAbsent(author.getAuthorId(), author);
    }

    /**
     * @return A list of all authors.
     */
    public List<Author> getAuthors() {
        return this.authorEntries.values().stream().toList();
    }


}
