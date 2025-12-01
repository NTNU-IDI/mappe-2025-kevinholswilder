package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     This class provides methods for searching {@link DiaryEntry} objects in the {@link DiaryEntry}.
 * </p>
 *
 * This class allows users to perform the following actions:
 * <ul>
 *     <li>Search {@link DiaryEntry} objects by title.</li>
 *     <li>Search {@link DiaryEntry} objects by {@link RecipeLabel}.</li>
 *     <li>Search {@link DiaryEntry} objects by {@link Author}.</li>
 *     <li>Search {@link DiaryEntry} objects after date.</li>
 *     <li>Search {@link DiaryEntry} objects between a period of dates.</li>
 *     <li>Search {@link DiaryEntry} objects by a given prompt.</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiarySearchUI {

    /**
     * Prompts the user to enter a title, which searches for {@link DiaryEntry},
     * entries whose titles match the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryByTitle(Scanner input) {
        System.out.println("What's the title of the entry you're trying to search for?");
        String title = UtilityManager.ensureNonEmptyTrimmedString(input);

        // Search for diary entries with the given title and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitle(title);
        DiaryHelper.performSearch(diaryEntries);
    }

    /**
     * Prompts the user to enter a date, which searches for {@link DiaryEntry},
     * entries whose date matches the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryByDate(Scanner input) {
        System.out.println("What's the date of the entry(ies) you're trying to search for (Format: YYYY-MM-DD)?");
        LocalDate date = UtilityManager.ensureValidDate(input);

        // Search for diary entries with the given date and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntryByDate(date);
        DiaryHelper.performSearch(diaryEntries);
    }

    /**
     * Prompts the user to enter a start and end date, which searches for {@link DiaryEntry},
     * entries whose date is between the given dates.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryBetweenPeriod(Scanner input) {
        System.out.println("What's the start date of the period you're trying to search for (Format: YYYY-MM-DD)?");
        LocalDate startDate = UtilityManager.ensureValidDate(input);
        System.out.println("What's the end date of the period you're trying to search for (Format: YYYY-MM-DD)?");
        LocalDate endDate = UtilityManager.ensureValidDate(input);

        // Search for diary entries within the given period and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesBetweenPeriod(startDate, endDate);
        DiaryHelper.performSearch(diaryEntries);
    }

    /**
     * Prompts the user to enter a username, which searches for {@link DiaryEntry},
     * entries written by the given {@link Author}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryByAuthor(Scanner input) {
        System.out.println("What's the username of the author you're trying to search for?");
        // List all authors.
        List<Author> authors = RegisterHandler.getAuthorRegister().getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + authors.get(i).getUsername());
        }

        // Check if the author exists.
        String username = UtilityManager.ensureNonEmptyTrimmedString(input);
        Author author = RegisterHandler.getAuthorRegister().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Author not found, please try again.");
            return;
        }

        // Search for diary entries written by the given author and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(author);
        DiaryHelper.performSearch(diaryEntries);
    }

    /**
     * Prompts the user to enter a prompt, which searches for {@link DiaryEntry},
     * entries whose content contains the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryByPrompt(Scanner input) {
        System.out.println("Please enter your search prompt:");
        String prompt = UtilityManager.ensureNonEmptyTrimmedString(input);

        // Search for diary entries where the content contains the prompt and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByPrompt(prompt);
        DiaryHelper.performSearch(diaryEntries);
    }

    /**
     * Prompts the user to enter a label name, which searches for {@link DiaryEntry},
     * entries whose list of labels contains the given {@link RecipeLabel}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void searchDiaryByLabel(Scanner input) {
        System.out.println("Please enter the label you're looking for:");
        // Print all available labels.
        System.out.println("Available Labels:");
        RecipeLabel.printLabels(EnumSet.allOf(RecipeLabel.class));

        // Get the label from the user.
        String labelString = UtilityManager.ensureNonEmptyTrimmedString(input);
        RecipeLabel label = RecipeLabel.tryParse(labelString);
        if (label == null) {
            System.out.println("Invalid label, please try again.");
            return;
        }

        // Search for diary entries with the given label and print them if any are found.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByLabel(label);
        DiaryHelper.performSearch(diaryEntries);
    }

}
