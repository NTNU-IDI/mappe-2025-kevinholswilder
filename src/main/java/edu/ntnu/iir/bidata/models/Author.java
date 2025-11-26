package main.java.edu.ntnu.iir.bidata.models;

import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

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

    /**
     * @param name Sets the name of an author.
     */
    public Author(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    /**
     * @return the name of an author.
     */
    public String getName()  {
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
     * Returns a formatted string representation of an author, where the name and surname are capitalized.
     *
     * @return a string in the format of "Name Surname".
     */
    @Override
    public String toString() {
        return UtilityManager.capitalize(this.getName()) + " " + UtilityManager.capitalize(this.getSurname());
    }
}
