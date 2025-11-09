package main.java.edu.ntnu.iir.bidata.models;

import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representation of a diary entry
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

public class DiaryEntry {

    private final String title;
    private final String content;
    private final Author author;
    private final LocalDate date;
    private final UUID diaryEntryId;

    /**
     * @param title Sets the title of a diary entry.
     * @param content Sets the content of a diary entry.
     * @param author Sets the author of a diary entry.
     */
    public DiaryEntry(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = LocalDate.now();
        this.diaryEntryId = UUID.randomUUID();
    }

    /**
     * @return the title of a diary entry.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the author of a diary entry.
     */
    public Author getAuthor() {
        return this.author;
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
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * @return ID of a diary entry.
     */
    public UUID getId() {
        return this.diaryEntryId;
    }

    /**
     * @return String representation of a diary entry.
     */
    @Override
    public String toString() {
        return UtilityManager.capitalize(this.title) +
                "\n------------------------" +
                "\n" + this.content.trim() +
                "\nDate: " + this.date +
                "\n " +
                "\nAuthor: " + this.author.toString() +
                "\n------------------------\n";
    }

}
