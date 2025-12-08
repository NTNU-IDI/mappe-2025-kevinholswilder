package main.java.edu.ntnu.iir.bidata.register;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;

/**
 * Represents the {@link DiaryEntry} register.
 *
 * <p>This class is a singleton class that ensures there only exists 1 instance of
 * the local register at a time to ensure a single source of truth
 * throughout the runtime of the program.
 * </p>
 *
 * @author Kevin Holswilder
 * @see <a href="https://www.geeksforgeeks.org/java/singleton-class-java/">Singleton Method Design Pattern in Java</a>
 * @since 2025/10/16
 */

public class DiaryRegister {

  private static DiaryRegister instance;

  private final HashMap<UUID, DiaryEntry> diaryEntries;

  /**
   * Private constructor to prevent instantiation.
   */
  private DiaryRegister() {
    this.diaryEntries = new HashMap<>();
  }

  /**
   * Returns the instance of the register.
   *
   * @return the single instance of the register.
   */
  static DiaryRegister getInstance() {
    if (instance == null) {
      instance = new DiaryRegister();
    }
    return instance;
  }

  /**
   * Adds the given {@link DiaryEntry} to the register.
   *
   * @param diaryEntry takes in a {@link DiaryEntry} to add to the register.
   */
  public void addDiaryEntry(DiaryEntry diaryEntry) {
    this.diaryEntries.putIfAbsent(diaryEntry.getId(), diaryEntry);
  }

  /**
   * Removes a {@link DiaryEntry} by using their ID.
   *
   * @param diaryId takes in the ID of a {@link DiaryEntry} to remove.
   */
  public void removeDiaryEntry(UUID diaryId) {
    this.diaryEntries.remove(diaryId);
  }

  /**
   * Returns a list of {@link DiaryEntry} objects where the input matches the title.
   *
   * @param title takes in a string.
   * @return a list with {@link DiaryEntry} objects where the title matches the given string.
   */
  public List<DiaryEntry> getDiaryEntriesByTitle(String title) {
    return this.getDiaryEntryStream().filter(it -> it.getTitle().equalsIgnoreCase(title))
        .toList();
  }

  /**
   * Returns a {@link DiaryEntry} where the author and title match.
   *
   * @param title  takes in a string.
   * @param author takes in a {@link Author}.
   * @return a {@link DiaryEntry} where the title and {@link Author} matches the given string,
   *     returns null if no entry is found.
   */
  public DiaryEntry getDiaryEntryByTitleAndAuthor(String title, Author author) {
    return this.getDiaryEntryStream()
        .filter(it -> it.getTitle().equalsIgnoreCase(title)
            && it.getAuthor().getUsername().equalsIgnoreCase(author.getUsername()))
        .findFirst()
        .orElse(null);
  }

  /**
   * Returns a list of {@link DiaryEntry} objects where the date matches the input.
   *
   * @param date takes in a {@link LocalDate}.
   * @return a list with {@link DiaryEntry} objects where the date matches the given date.
   */
  public List<DiaryEntry> getDiaryEntryByDate(LocalDate date) {
    return this.getDiaryEntryStream()
        .filter(it -> it.getDate().equals(date))
        .toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} objects where the date
   * is in between the start and end date.
   *
   * @param start takes in a {@link LocalDate} of the start of the period.
   * @param end   takes in a {@link LocalDate} of the end of the period.
   * @return a list with {@link DiaryEntry} objects where the date is between the given dates.
   */
  public List<DiaryEntry> getDiaryEntriesBetweenPeriod(LocalDate start, LocalDate end) {
    return this.getDiaryEntryStream()
        .filter(it -> !it.getDate().isBefore(start) && !it.getDate().isAfter(end))
        .toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} objects where the content contains the input.
   *
   * @param prompt takes in a string.
   * @return a list with {@link DiaryEntry} objects where the content contains the given string.
   */
  public List<DiaryEntry> getDiaryEntriesByPrompt(String prompt) {
    return this.getDiaryEntryStream()
        .filter(it -> it.getContent().toLowerCase().contains(prompt.toLowerCase()))
        .toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} objects where the recipe labels
   * contain the given {@link RecipeLabel}.
   *
   * @param recipeLabel takes in a {@link RecipeLabel}.
   * @return a list with {@link DiaryEntry} objects whose list of labels match the given label.
   */
  public List<DiaryEntry> getDiaryEntriesByLabel(RecipeLabel recipeLabel) {
    return this.getDiaryEntryStream()
        .filter(it -> it.getRecipeLabels().contains(recipeLabel))
        .toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} where the author matches.
   *
   * @param author takes in an {@link Author}.
   * @return a list of {@link DiaryEntry} objects where the input matches the given {@link Author}.
   */
  public List<DiaryEntry> getDiaryEntriesByAuthor(Author author) {
    return this.getDiaryEntryStream()
        .filter(it -> it.getAuthor().getUsername().equalsIgnoreCase(author.getUsername()))
        .toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} objects in the register.
   *
   * @return a list of all {@link DiaryEntry} objects in the register.
   */
  public List<DiaryEntry> getDiaryEntries() {
    return this.getDiaryEntryStream().toList();
  }

  /**
   * Returns a list of {@link DiaryEntry} objects, sorted by their date.
   *
   * @return a sorted list of {@link DiaryEntry} objects by date.
   */
  public List<DiaryEntry> getDiaryEntriesSortedByDate() {
    return this.getDiaryEntryStream().sorted(Comparator.comparing(DiaryEntry::getDate)).toList();
  }

  /**
   * Returns a stream of {@link DiaryEntry} objects.
   *
   * <p><i>Acts as a helper function in this class.</i></p>
   *
   * @return a stream of {@link DiaryEntry} objects.
   */
  private Stream<DiaryEntry> getDiaryEntryStream() {
    return this.diaryEntries.values().stream();
  }

}
