package main.java.edu.ntnu.iir.bidata.diary;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Handles diary-related operations.
 *
 * @Author Kevin Holswilder
 * @Date 01.11.2025
 */

public class DiaryHandler {

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void writeDiary(Scanner input) {
        System.out.println("What's the title of your diary entry?");
        String title = UtilityManager.ensureNonEmptyString(input);

        System.out.println("What's the content of your diary entry?");
        String content = UtilityManager.readMultiLineInput(input);

        // Create a new diary entry.
        DiaryEntry diaryEntry = new DiaryEntry(title, content);
        diaryEntry.addAuthor(UserHandler.getCurrentUser());

        // Add the diary entry to the database.
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry);
        System.out.println("Diary entry successfully written.");
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void deleteDiary(Scanner input) {
        System.out.println("What's the title of the entry you're trying to delete?");
        String title = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        DiaryEntry diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntryByTitle(title);
        if (diaryEntry == null) {
            System.out.println("Entry not found, please try again.");
            return;
        }

        // Deletion of diary entry.
        RegisterHandler.getDiaryDatabase().removeDiaryEntry(diaryEntry.getId());
        System.out.println("Diary entry successfully deleted.");
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByTitle(Scanner input) {
        System.out.println("What's the title of the entry you're trying to search for?");
        String title = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        DiaryEntry diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntryByTitle(title);
        if (diaryEntry == null) {
            System.out.println("Entry not found, please try again.");
            return;
        }

        System.out.println("Diary entry found.");
        System.out.println(diaryEntry);
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByDate(Scanner input) {
        System.out.println("What's the date of the entry(ies) you're trying to search for (Example: yyyy-MM-dd)?");
        LocalDate date = UtilityManager.ensureValidDate(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntryByDate(date);
        if (diaryEntry == null) {
            System.out.println("Entry(ies) not found, please try again.");
            return;
        }

        System.out.println("Diary(ies) entry found.");
        for (DiaryEntry entry : diaryEntry) {
            System.out.println(entry.toString());
        }
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByPeriod(Scanner input) {
        System.out.println("What's the start date of the period you're trying to search for?");
        LocalDate startDate = UtilityManager.ensureValidDate(input);
        System.out.println("What's the end date of the period you're trying to search for?");
        LocalDate endDate = UtilityManager.ensureValidDate(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntriesByPeriod(startDate, endDate);
        if (diaryEntry == null) {
            System.out.println("Entry(ies) not found, please try again.");
            return;
        }

        System.out.println("Diary(ies) entry found.");
        for (DiaryEntry entry : diaryEntry) {
            System.out.println(entry.toString());
        }
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByAuthor(Scanner input) {
        System.out.println("What's the username of the author you're trying to search for?");
        String username = UtilityManager.ensureNonEmptyString(input);

        // Check if the author exists.
        Author author = RegisterHandler.getAuthorDatabase().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Author not found, please try again.");
            return;
        }

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntriesByAuthor(author);
        if (diaryEntry.isEmpty()) {
            System.out.println("Entry(ies) not found, please try again.");
            return;
        }

        System.out.println("Diary(ies) entry found.");
        for (DiaryEntry entry : diaryEntry) {
            System.out.println(entry.toString());
        }
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByPrompt(Scanner input) {
        System.out.println("Please enter your search prompt:");
        String prompt = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntriesByPrompt(prompt);
        if (diaryEntry.isEmpty()) {
            System.out.println("Entry(ies) not found, please try again.");
            return;
        }

        System.out.println("Diary(ies) entry found.");
        for (DiaryEntry entry : diaryEntry) {
            System.out.println(entry.toString());
        }
    }

}
