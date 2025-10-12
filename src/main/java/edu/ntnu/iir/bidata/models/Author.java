package main.java.edu.ntnu.iir.bidata.models;

import javax.persistence.Entity;
import java.util.UUID;

/**
 * Representation of an author
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

@Entity
public class Author {

    private final String name;
    private final UUID authorId;

    /**
     * @param name Sets the name of an author.
     */
    public Author(String name) {
        this.name = name;
        this.authorId = UUID.randomUUID();
    }

    /**
     * @return the name of an author.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the id of an author.
     */
    public UUID getAuthorId() {
        return this.authorId;
    }
}
