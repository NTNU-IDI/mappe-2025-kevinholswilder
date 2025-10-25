package main.java.edu.ntnu.iir.bidata.models;

import java.util.UUID;

/**
 * Representation of an author
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

public class Author {

    private final String username;
    private final String name;
    private final String surname;
    private final UUID authorId;

    /**
     * @param name Sets the name of an author.
     */
    public Author(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.authorId = UUID.randomUUID();
    }

    /**
     * @return the name of an author.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the surname of an author.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return the username of an author.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the id of an author.
     */
    public UUID getAuthorId() {
        return this.authorId;
    }

    /**
     * @return String representation of an author.
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
