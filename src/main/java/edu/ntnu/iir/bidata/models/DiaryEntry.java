package main.java.edu.ntnu.iir.bidata.models;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.UUID;

/**
 * <p>Represents a diary entry with a unique id, title, content, author, date and labels.
 * Additionally, this class provides both getter and helper methods to access the diary entry's attributes.</p>
 *
 * Example usage:
 * <pre>
 *     DiaryEntry diaryEntry = new DiaryEntry("Chicken Curry", "A simple curry recipe.", UserHandler.getCurrentUser());
 *     System.out.println(diaryEntry.getTitle()); // Chicken Curry
 * </pre>
 *
 * @author Kevin Holswilder
 * @since 2025/10/12
 * @see UserHandler#getCurrentUser() to see how the author is obtained.
 */

public class DiaryEntry {

    private final String title;
    private String content;
    private final Author author;
    private final LocalDate date;
    private final UUID diaryEntryId;
    private final HashSet<RecipeLabel> recipeLabels;

    /**
     * Creates a new {@link DiaryEntry} object with the given parameters.
     * The diary entry's id is generated randomly using a {@link UUID}.
     *
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
        this.recipeLabels = new HashSet<>();
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
     * Replaces the line of a diary entry's content with new content.
     *
     * @param lineNumber the line number of the content to be replaced.
     * @param newContent the new content to replace the old content.
     */
    public void setContentLine(int lineNumber, String newContent) {
        String[] contentLines = this.content.split("\n");
        contentLines[lineNumber - 1] = newContent;
        this.content = String.join("\n", contentLines);
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
     * @return the labels of a diary entry.
     */
    public HashSet<RecipeLabel> getRecipeLabels() {
        return this.recipeLabels;
    }

    /**
     * @param recipeLabel to add in the recipe labels.
     */
    public void addRecipeLabel(RecipeLabel recipeLabel) {
        this.recipeLabels.add(recipeLabel);
    }

    /**
     * @param recipeLabel to remove from the recipe labels.
     */
    public void removeRecipeLabel(RecipeLabel recipeLabel) {
        this.recipeLabels.remove(recipeLabel);
    }

    /**
     * Formats the recipe labels into a string.
     * @return a formatted string of the recipe labels.
     */
    private String getFormattedLabels() {
        if (this.recipeLabels.isEmpty()) {
            return "None";
        } else {
            return String.join(", ", this.recipeLabels.stream().map(label -> UtilityManager.capitalize(label.toString().toLowerCase())).toList());
        }
    }

    /**
     * @return a formatted string representation of a {@link DiaryEntry}.
     */
    @Override
    public String toString() {
        return UtilityManager.capitalize(this.title) +
                "\n------------------------" +
                "\n" + this.content.trim() +
                "\n " +
                "\nLabels: " + this.getFormattedLabels() +
                "\n " +
                "\nDate: " + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(this.date) +
                "\nAuthor: " + this.author.toString() +
                "\n------------------------\n";
    }

}
