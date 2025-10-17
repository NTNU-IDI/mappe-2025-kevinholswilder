package main.java.edu.ntnu.iir.bidata.database;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Singleton class that ensures there only exists 1 instance of
 * the local database to ensure a single source of truth
 *
 * @Author Kevin Holswilder
 * @Date 16.10.2025
 */

public class DatabaseHandler {

    public static DatabaseHandler instance;

    private final HashMap<UUID, DiaryEntry> diaryEntries;

    /**
     * Representation of the database constructor.
     */
    private DatabaseHandler() {
        try {
            this.diaryEntries = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException("Could not establish database.", e.getCause());
        }
    }

    /**
     * @return the single instance of the database.
     */
    private static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    /**
     * @param diaryEntry takes in a [DiaryEntry] to add to the database.
     */
    public void addDiaryEntry(DiaryEntry diaryEntry) {
        this.diaryEntries.putIfAbsent(UUID.randomUUID(), diaryEntry);
    }

    /**
     * @param diaryId takes in the ID of a [DiaryEntry] to remove.
     */
    public void removeDiaryEntry(UUID diaryId) {
        this.diaryEntries.remove(diaryId);
    }

    /**
     * @param date takes in the date of the written dairy.
     * @return null if no diary can be found in the database.
     */
    public DiaryEntry getDiaryEntryByDate(LocalDateTime date) {
        return this.diaryEntries.values().stream()
                .filter(it -> it.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param start start of the period.
     * @param end end of the period.
     * @return a list with [DiaryEntry] in the period from start to end.
     */
    public List<DiaryEntry> getDiaryEntriesByPeriod(LocalDateTime start, LocalDateTime end) {
        return this.diaryEntries.values().stream()
                .filter(it -> it.getDate().isAfter(start) && it.getDate().isBefore(end))
                .toList();
    }

    /**
     * @param author takes in an [Author].
     * @return a list with [DiaryEntry] objects of the given [Author].
     */
    public List<DiaryEntry> getDiaryEntriesByAuthor(Author author) {
        return this.diaryEntries.values().stream()
                .filter(it -> it.getAuthors().containsKey(author.getAuthorId()))
                .toList();
    }

    /**
     * @return a list of all [DiaryEntry] objects.
     */
    public List<DiaryEntry> getDiaryEntries() {
        return this.diaryEntries.values().stream().toList();
    }

}
