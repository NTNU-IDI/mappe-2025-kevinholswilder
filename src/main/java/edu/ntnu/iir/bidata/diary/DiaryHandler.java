package main.java.edu.ntnu.iir.bidata.diary;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
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
        // Check if the title already exists from the current author.
        String title;
        while (true) {
            System.out.println("Enter the title of your recipe diary:");
            title = UtilityManager.ensureNonEmptyString(input).trim();

            boolean titleExists = RegisterHandler.getDiaryDatabase().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser()) == null;
            if (!titleExists) {
                System.out.println("You already have a recipe with this title, please enter a new title.");
            } else {
                break;
            }
        }

        System.out.println("Write the content for your cooking diary:\nType 'exit' to save and finish writing.");
        String content = UtilityManager.readMultiLineInput(input);

        // Get the current author.
        Author author = UserHandler.getCurrentUser();
        // Create a new diary entry.
        DiaryEntry diaryEntry = new DiaryEntry(title, content, author);

        // Add (optional) labels to the diary entry.
        System.out.println("Add one or more labels to your recipe (optional):\nType 'exit' to finish or skip.");
        System.out.println("Available Labels:");
        for (RecipeLabel label : RecipeLabel.values()) {
            System.out.println(" - " + label.name());
        }

        List<String> labelList = UtilityManager.readMultiLineInputList(input);
        for (String labelString : labelList) {
            try {
                // Parse the string to a [RecipeLabel] object.
                RecipeLabel label = RecipeLabel.valueOf(labelString.toUpperCase());
                diaryEntry.addRecipeLabel(label);
            } catch (IllegalArgumentException e) {
                // If the string is empty, ignore.
                if (!labelString.isEmpty()) {
                    System.out.println("Ignoring invalid label: " + labelString + ".");
                }
            }
        }

        // Add the diary entry to the database.
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry);
        System.out.println("Your recipe diary has been saved.");
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void deleteDiary(Scanner input) {
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
        if (diaryEntries.isEmpty()) {
            System.out.println("You have not written any recipes yet.");
            return;
        }
        System.out.println("Enter the title of your recipe diary you're trying to delete:");
        for (int i = 0; i < diaryEntries.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + diaryEntries.get(i).getTitle());
        }
        String title = UtilityManager.ensureNonEmptyString(input).trim();

        // Check if the diary entry exists for the current author.
        DiaryEntry diaryEntry = RegisterHandler.getDiaryDatabase().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser());
        if (diaryEntry == null) {
            System.out.println("No entry was found.");
            return;
        }

        RegisterHandler.getDiaryDatabase().removeDiaryEntry(diaryEntry.getId());
        System.out.println("Recipe diary has been deleted.");
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByTitle(Scanner input) {
        System.out.println("What's the title of the entry you're trying to search for?");
        String title = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByTitle(title);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByDate(Scanner input) {
        System.out.println("What's the date of the entry(ies) you're trying to search for (Example: YYYY-MM-DD)?");
        LocalDate date = UtilityManager.ensureValidDate(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntryByDate(date);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
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
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByPeriod(startDate, endDate);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByAuthor(Scanner input) {
        System.out.println("What's the username of the author you're trying to search for?");
        // List all authors.
        List<Author> authors = RegisterHandler.getAuthorDatabase().getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + authors.get(i).getUsername());
        }
        String username = UtilityManager.ensureNonEmptyString(input);

        // Check if the author exists.
        Author author = RegisterHandler.getAuthorDatabase().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Author not found, please try again.");
            return;
        }

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByAuthor(author);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByPrompt(Scanner input) {
        System.out.println("Please enter your search prompt:");
        String prompt = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByPrompt(prompt);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * @param input Takes in a scanner for user input.
     */
    public static void searchDiaryByLabel(Scanner input) {
        System.out.println("Please enter the label you're looking for:");
        System.out.println("Available Labels:");
        for (RecipeLabel label : RecipeLabel.values()) {
            System.out.println(" - " + label.name());
        }
        String labelString = UtilityManager.ensureNonEmptyString(input);

        RecipeLabel label;
        try {
            label = RecipeLabel.valueOf(labelString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid label, please try again.");
            return;
        }

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesByLabel(label);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Lists all diary entries in the register.
     */
    public static void exportDiaries() {
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntriesSortedByDate();
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Lists all objects of [DiaryEntry] in the given list.
     * @param diaryEntries takes in a list of [DiaryEntry] objects.
     */
    private static void listDiaries(List<DiaryEntry> diaryEntries) {
        System.out.println("=== Recipe Entries ===");
        for (int i = 0; i < diaryEntries.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + diaryEntries.get(i));
        }
    }

}
