package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     Provides helper methods for the diary UI.
 *     Additionally, these methods are package-private to prevent direct access from other classes.
 * </p>
 *
 * Methods in this class include:
 * <ul>
 *     <li>Performing searched on a list of {@link DiaryEntry} objects and printing the results.</li>
 *     <li>Listing all {@link DiaryEntry} objects in a given list.</li>
 *     <li>Printing the titles of {@link DiaryEntry} objects in a list.</li>
 *     <li>Prompting the user to enter a valid diary title that exists for the current user.</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiaryHelper {

    /**
     * Performs a search based on the given list of {@link DiaryEntry} objects.
     */
    static void performSearch(List<DiaryEntry> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }
        listDiaries(results);
    }

    /**
     * Lists all diaries in the given list.
     * @param diaryEntries a list of {@link DiaryEntry} objects.
     */
    static void listDiaries(List<DiaryEntry> diaryEntries) {
        System.out.println("=== Recipe Entries ===");
        for (int i = 0; i < diaryEntries.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + diaryEntries.get(i));
        }
    }

    /**
     * Prints all diary titles in the given list.
     *
     * @param diaryEntries a list of {@link DiaryEntry} objects.
     */
    static void printDiaryTitles(List<DiaryEntry> diaryEntries) {
        for (int i = 0; i < diaryEntries.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + diaryEntries.get(i).getTitle());
        }
    }

    /**
     * Gets a valid diary title that belongs to the current user,
     * prompting the user to enter a new title if the title does not exist.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    static String getValidDiaryTitle(Scanner input) {
        String title;
        while (true) {
            System.out.println("Enter the title of your recipe diary:");
            title = UtilityManager.ensureNonEmptyTrimmedString(input);

            boolean titleExists = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser()) != null;
            if (titleExists) {
                break;
            } else {
                System.out.println("You do not have a recipe with this title, please enter another title.");
            }
        }
        return title;
    }

}
