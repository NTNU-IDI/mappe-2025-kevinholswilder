package main.java.edu.ntnu.iir.bidata.register;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Singleton class that ensures there only exists 1 instance of
 * the local database to ensure a single source of truth
 *
 * @Author Kevin Holswilder
 * @Date 16.10.2025
 */

public class DiaryRegister {

    private static DiaryRegister instance;

    private final HashMap<UUID, DiaryEntry> diaryEntries;

    /**
     * Representation of the database constructor.
     */
    private DiaryRegister() {
        try {
            this.diaryEntries = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException("Could not establish database.", e.getCause());
        }
    }

    /**
     * @return the single instance of the database.
     */
    public static DiaryRegister getInstance() {
        if (instance == null) {
            instance = new DiaryRegister();
        }
        return instance;
    }

    /**
     * @param diaryEntry takes in a [DiaryEntry] to add to the database.
     */
    public void addDiaryEntry(DiaryEntry diaryEntry) {
        this.diaryEntries.putIfAbsent(diaryEntry.getId(), diaryEntry);
    }

    /**
     * @param diaryId takes in the ID of a [DiaryEntry] to remove.
     */
    public void removeDiaryEntry(UUID diaryId) {
        this.diaryEntries.remove(diaryId);
    }

    /**
     * @param title takes in a string.
     */
    public List<DiaryEntry> getDiaryEntriesByTitle(String title) {
        return this.getDiaryEntryStream().filter(it -> it.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    /**
     * @param title takes in a string.
     * @param author takes in an [Author].
     * @return a [DiaryEntry] object where the title and author match, if none match, it returns null.
     */
    public DiaryEntry getDiaryEntriesByTitleAndAuthor(String title, Author author) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getTitle().equalsIgnoreCase(title) && it.getAuthor().getAuthorId().equals(author.getAuthorId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param date takes in the date of the written dairy.
     * @return null if no diary can be found in the database.
     */
    public List<DiaryEntry> getDiaryEntryByDate(LocalDate date) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getDate().equals(date))
                .toList();
    }

    /**
     * @param start start of the period.
     * @param end end of the period.
     * @return a list with [DiaryEntry] in the period from start to end.
     */
    public List<DiaryEntry> getDiaryEntriesByPeriod(LocalDate start, LocalDate end) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getDate().isAfter(start) && it.getDate().isBefore(end))
                .toList();
    }

    /**
     * @param prompt takes in a string.
     * @return a list with [DiaryEntry] objects where the content contains the given string.
     */
    public List<DiaryEntry> getDiaryEntriesByPrompt(String prompt) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getContent().toLowerCase().contains(prompt.toLowerCase()))
                .toList();
    }

    /**
     * @param recipeLabel takes in a string.
     * @return a list with [DiaryEntry] objects where the list of label contains the given [RecipeLabel].
     */
    public List<DiaryEntry> getDiaryEntriesByLabel(RecipeLabel recipeLabel) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getRecipeLabels().contains(recipeLabel))
                .toList();
    }

    /**
     * @param author takes in an [Author].
     * @return a list with [DiaryEntry] objects of the given [Author].
     */
    public List<DiaryEntry> getDiaryEntriesByAuthor(Author author) {
        return this.getDiaryEntryStream()
                .filter(it -> it.getAuthor().getAuthorId().equals(author.getAuthorId()))
                .toList();
    }

    /**
     * @return a list of all [DiaryEntry] objects.
     */
    public List<DiaryEntry> getDiaryEntries() {
        return this.getDiaryEntryStream().toList();
    }

    /**
     * @return a sorted list of [DiaryEntry] objects, sorted after date.
     */
    public List<DiaryEntry> getDiaryEntriesSortedByDate() {
        return this.getDiaryEntryStream().sorted(Comparator.comparing(DiaryEntry::getDate)).toList();
    }

    /**
     * @return a stream of [DiaryEntry] objects.
     */
    private Stream<DiaryEntry> getDiaryEntryStream() {
        return this.diaryEntries.values().stream();
    }

}
