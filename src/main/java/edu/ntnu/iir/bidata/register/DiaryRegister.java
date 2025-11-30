package main.java.edu.ntnu.iir.bidata.register;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Represents the {@link DiaryEntry} register.
 *
 * <p>This class is a singleton class that ensures there only exists 1 instance of
 * the local register at a time to ensure a single source of truth throughout the runtime of the program.</p>
 *
 * @Author Kevin Holswilder
 * @Date 2025/10/16
 * @see <a href="https://www.geeksforgeeks.org/java/singleton-class-java/">Singleton Method Design Pattern in Java</a>
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
     * @return the single instance of the register.
     */
    static DiaryRegister getInstance() {
        if (instance == null) {
            instance = new DiaryRegister();
        }
        return instance;
    }

    /**
     * @param diaryEntry takes in a {@link DiaryEntry} to add to the register.
     */
    public void addDiaryEntry(DiaryEntry diaryEntry) {
        this.diaryEntries.putIfAbsent(diaryEntry.getId(), diaryEntry);
    }

    /**
     * @param diaryId takes in the ID of a {@link DiaryEntry} to remove.
     */
    public void removeDiaryEntry(UUID diaryId) {
        this.diaryEntries.remove(diaryId);
    }

    /**
     * @param title takes in a string.
     * @return a list with {@link DiaryEntry} objects where the title matches the given string.
     */
    public List<DiaryEntry> getDiaryEntriesByTitle(String title) {
        return this.getDiaryEntryStream().filter(it -> it.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    /**
     * @param title takes in a string.
     * @param author takes in a {@link Author}.
     * @return a {@link DiaryEntry} where the title and {@link Author} matches the given string, returns null if no entry is found.
     */
    public DiaryEntry getDiaryEntriesByTitleAndAuthor(String title, Author author) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getTitle().equalsIgnoreCase(title) && it.getAuthor().getUsername().equalsIgnoreCase(author.getUsername()))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param date takes in a {@link LocalDate}.
     * @return a list with {@link DiaryEntry} objects where the date matches the given date.
     */
    public List<DiaryEntry> getDiaryEntryByDate(LocalDate date) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getDate().equals(date))
                .toList();
    }

    /**
     * @param start takes in a {@link LocalDate} of the start of the period.
     * @param end takes in a {@link LocalDate} of the end of the period.
     * @return a list with {@link DiaryEntry} objects where the date is between the given dates.
     */
    public List<DiaryEntry> getDiaryEntriesByPeriod(LocalDate start, LocalDate end) {
        return this.getDiaryEntryStream()
                .filter(it -> !it.getDate().isBefore(start) && !it.getDate().isAfter(end))
                .toList();
    }

    /**
     * @param prompt takes in a string.
     * @return a list with {@link DiaryEntry} objects where the content contains the given string.
     */
    public List<DiaryEntry> getDiaryEntriesByPrompt(String prompt) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getContent().toLowerCase().contains(prompt.toLowerCase()))
                .toList();
    }

    /**
     * @param recipeLabel takes in a {@link RecipeLabel}.
     * @return a list with {@link DiaryEntry} objects whose list of labels match the given label.
     */
    public List<DiaryEntry> getDiaryEntriesByLabel(RecipeLabel recipeLabel) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getRecipeLabels().contains(recipeLabel))
                .toList();
    }

    /**
     * @param author takes in an {@link Author}.
     * @return a list with {@link DiaryEntry} objects where the parameter matches the given {@link Author}.
     */
    public List<DiaryEntry> getDiaryEntriesByAuthor(Author author) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getAuthor().getUsername().equalsIgnoreCase(author.getUsername()))
                .toList();
    }

    /**
     * @return a list of all {@link DiaryEntry} objects in the register.
     */
    public List<DiaryEntry> getDiaryEntries() {
        return this.getDiaryEntryStream().toList();
    }

    /**
     * @return a sorted list of {@link DiaryEntry} objects by date.
     */
    public List<DiaryEntry> getDiaryEntriesSortedByDate() {
        return this.getDiaryEntryStream().sorted(Comparator.comparing(DiaryEntry::getDate)).toList();
    }

    /**
     * @return a stream of {@link DiaryEntry} objects.
     */
    private Stream<DiaryEntry> getDiaryEntryStream() {
        return this.diaryEntries.values().stream();
    }

}
