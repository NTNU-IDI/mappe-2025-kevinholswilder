package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.DiaryRegister;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *     This class provides methods for exporting {@link DiaryEntry} objects and print them to the console.
 * </p>
 *
 * This class allows users to perform the following actions:
 * <ul>
 *     <li>Export all {@link DiaryEntry} objects in the register.</li>
 *     <li>Export statistics from each {@link Author} with the amount of {@link DiaryEntry} entries they have written.</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiaryExportUI {

    /**
     * Exports statistics from each author with the amount of {@link DiaryEntry} entries they have written.
     */
    public void exportAuthorStatistics() {
        List<Author> authors = RegisterHandler.getAuthorRegister().getAuthors()
                .stream().sorted(Comparator.comparing(Author::getUsername)).toList();

        System.out.println("Number of recipes written by each author:");
        System.out.println("-----------------------------------------");

        for (Author author : authors) {
            List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(author);
            int diaryCount = diaryEntries.size();
            System.out.println(author.toString() + ": " + diaryCount);
        }
    }

    /**
     * Exports all {@link DiaryEntry} objects in the {@link DiaryRegister}, sorted by date.
     */
    public void exportDiaries() {
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesSortedByDate();
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }
        DiaryHelper.listDiaries(diaryEntries);
    }

}
