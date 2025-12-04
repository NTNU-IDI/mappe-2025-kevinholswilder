package main.java.edu.ntnu.iir.bidata.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.UUID;
import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.service.UserService;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

/**
 * Represents a diary entry with a unique id, title, content, author, date and labels.
 * Additionally, this class provides both getter
 * and helper methods to access the diary entry's attributes.
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 *     DiaryEntry diaryEntry = new DiaryEntry("Soup", "An easy meal", UserService.getCurrentUser());
 *     System.out.println(diaryEntry.getTitle()); // Chicken Curry
 * </pre>
 *
 * @author Kevin Holswilder
 * @see UserService#getCurrentUser() to see how the author is obtained.
 * @since 2025/10/12
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
   * @param title   Sets the title of a diary entry.
   * @param content Sets the content of a diary entry.
   * @param author  Sets the author of a diary entry.
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
   * Getter method for the title of a diary entry.
   *
   * @return the title of a {@link DiaryEntry}.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Getter method for the author of a {@link DiaryEntry}.
   *
   * @return the author of a diary entry.
   */
  public Author getAuthor() {
    return this.author;
  }

  /**
   * Getter method for the content of a {@link DiaryEntry}.
   *
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
   * Getter method for the creation date of a {@link DiaryEntry}.
   *
   * @return the date of a diary entry.
   */
  public LocalDate getDate() {
    return this.date;
  }

  /**
   * Getter method for the ID of a {@link DiaryEntry}.
   *
   * @return ID of a diary entry.
   */
  public UUID getId() {
    return this.diaryEntryId;
  }

  /**
   * Getter method for the recipe labels of a {@link DiaryEntry}.
   *
   * @return the labels of a diary entry.
   */
  public HashSet<RecipeLabel> getRecipeLabels() {
    return this.recipeLabels;
  }

  /**
   * Adds a {@link RecipeLabel} to the recipe labels of a {@link DiaryEntry}.
   *
   * @param recipeLabel to add in the recipe labels.
   */
  public void addRecipeLabel(RecipeLabel recipeLabel) {
    this.recipeLabels.add(recipeLabel);
  }

  /**
   * Removes a {@link RecipeLabel} from the recipe labels of a {@link DiaryEntry}.
   *
   * @param recipeLabel to remove from the recipe labels.
   */
  public void removeRecipeLabel(RecipeLabel recipeLabel) {
    this.recipeLabels.remove(recipeLabel);
  }

  /**
   * Formats the recipe labels into a string.
   *
   * @return a formatted string of the recipe labels.
   */
  private String getFormattedLabels() {
    if (this.recipeLabels.isEmpty()) {
      return "None";
    } else {
      return String.join(", ", this.recipeLabels.stream()
          .map(label -> UtilityManager.capitalize(label.toString().toLowerCase())).toList());
    }
  }

  /**
   * Returns a formatted string representation of a {@link DiaryEntry}.
   *
   * @return a formatted string representation of a {@link DiaryEntry}.
   */
  @Override
  public String toString() {
    return UtilityManager.capitalize(this.title)
        + "\n------------------------"
        + "\n" + this.content.trim()
        + "\n "
        + "\nLabels: " + this.getFormattedLabels()
        + "\n "
        + "\nDate: " + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(this.date)
        + "\nAuthor: " + this.author.toString()
        + "\n------------------------\n";
  }

}
