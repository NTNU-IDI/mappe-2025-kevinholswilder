package main.java.edu.ntnu.iir.bidata.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

/**
 * Representation of a diary entry
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

public class DiaryEntry {

    private String title;
    private final HashMap<UUID, Author> authors;
    private String content;
    private final LocalDateTime date;

    /**
     * @param title Sets the title of a diary entry.
     * @TODO Change the constructor to take a complete entry of a diary.
     */
    public DiaryEntry(String title) {
        this.title = title;
        this.authors = new HashMap<>();
        this.content = "";
        this.date = LocalDateTime.now();
    }

    /**
     * @param title Sets the title of a diary entry.
     * @throws IllegalArgumentException if the title is empty.
     */
    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    /**
     * @return the title of a diary entry.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the authors of a diary entry.
     */
    public HashMap<UUID, Author> getAuthors() {
        return this.authors;
    }

    /**
     * @param content Sets the content of a diary entry.
     * @throws IllegalArgumentException if the content is empty.
     */
    public void setContent(String content) {
        if (content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.content = content;
    }

    /**
     * @return the content of a diary entry.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return the date of a diary entry.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * @param author Adds an author to a diary entry.
     */
    public void addAuthor(Author author) {
        this.authors.putIfAbsent(
                author.getAuthorId(),
                author
        );
    }

    /**
     * @param author Removes an author from a diary entry.
     */
    public void removeAuthor(Author author) {
        this.authors.remove(author.getAuthorId());
    }
}
