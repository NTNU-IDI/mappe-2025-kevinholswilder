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
 * Provides methods for creating, searching, deleting, and exporting diary entries.
 * <p>
 *     This class is responsible for handling all interactions between the console UI and the register.
 * </p>
 * <p>This class allows users the following:</p>
 * <ul>
 *     <li>Write Diary Entries.</li>
 *     <li>Delete Diary Entries.</li>
 *     <li>Search {@link DiaryEntry} objects by title.</li>
 *     <li>Search {@link DiaryEntry} objects by {@link RecipeLabel}.</li>
 *     <li>Search {@link DiaryEntry} objects by {@link Author}.</li>
 *     <li>Search {@link DiaryEntry} objects after date.</li>
 *     <li>Search {@link DiaryEntry} objects between a period.</li>
 *     <li>Search {@link DiaryEntry} objects by a given prompt.</li>
 *     <li>Export all {@link DiaryEntry} objects.</li>
 *     <li>Export statistics from each {@link Author} with the amount of {@link DiaryEntry} objects they have written.</li>
 * </ul>
 *
 * @Author Kevin Holswilder
 * @Date 2025/11/01
 */

public class DiaryHandler {

    /**
     * Prompts the user to enter a title which has to be unique for the {@link Author},
     * write the content, and additionally add {@link RecipeLabel}s to the diary entry.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void writeDiary(Scanner input) {
        // Check if the title already exists from the current author.
        String title;
        while (true) {
            System.out.println("Enter the title of your recipe diary:");
            title = UtilityManager.ensureNonEmptyString(input).trim();

            boolean titleExists = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser()) == null;
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

        // Add the diary entry to the register.
        RegisterHandler.getDiaryRegister().addDiaryEntry(diaryEntry);
        System.out.println("Your recipe diary has been saved.");
    }

    /**
     * Prompts the user to enter the title of the {@link DiaryEntry} they wish to delete.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void deleteDiary(Scanner input) {
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
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
        DiaryEntry diaryEntry = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser());
        if (diaryEntry == null) {
            System.out.println("No entry was found.");
            return;
        }

        RegisterHandler.getDiaryRegister().removeDiaryEntry(diaryEntry.getId());
        System.out.println("Recipe diary has been deleted.");
    }

    /**
     * Prompts the user to enter a title, which searches for {@link DiaryEntry},
     * entries whose titles match the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void searchDiaryByTitle(Scanner input) {
        System.out.println("What's the title of the entry you're trying to search for?");
        String title = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitle(title);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Prompts the user to enter a date, which searches for {@link DiaryEntry},
     * entries whose date matches the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void searchDiaryByDate(Scanner input) {
        System.out.println("What's the date of the entry(ies) you're trying to search for (Example: YYYY-MM-DD)?");
        LocalDate date = UtilityManager.ensureValidDate(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntryByDate(date);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Prompts the user to enter a start and end date, which searches for {@link DiaryEntry},
     * entries whose date is between the given dates.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void searchDiaryByPeriod(Scanner input) {
        System.out.println("What's the start date of the period you're trying to search for?");
        LocalDate startDate = UtilityManager.ensureValidDate(input);
        System.out.println("What's the end date of the period you're trying to search for?");
        LocalDate endDate = UtilityManager.ensureValidDate(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByPeriod(startDate, endDate);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Prompts the user to enter a username, which searches for {@link DiaryEntry},
     * entries written by the given {@link Author}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void searchDiaryByAuthor(Scanner input) {
        System.out.println("What's the username of the author you're trying to search for?");
        // List all authors.
        List<Author> authors = RegisterHandler.getAuthorRegister().getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + authors.get(i).getUsername());
        }
        String username = UtilityManager.ensureNonEmptyString(input);

        // Check if the author exists.
        Author author = RegisterHandler.getAuthorRegister().getAuthorByUsername(username);
        if (author == null) {
            System.out.println("Author not found, please try again.");
            return;
        }

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(author);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Prompts the user to enter a prompt, which searches for {@link DiaryEntry},
     * entries whose content contains the given query.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public static void searchDiaryByPrompt(Scanner input) {
        System.out.println("Please enter your search prompt:");
        String prompt = UtilityManager.ensureNonEmptyString(input);

        // Check if the diary entry exists.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByPrompt(prompt);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Prompts the user to enter a label name, which searches for {@link DiaryEntry},
     * entries whose list of labels contains the given {@link RecipeLabel}.
     *
     * @param input a {@link Scanner} object to read user input.
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
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByLabel(label);
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Exports all diary entries in the register, sorted by date.
     */
    public static void exportDiaries() {
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesSortedByDate();
        if (diaryEntries.isEmpty()) {
            System.out.println("No entries were found.");
            return;
        }

        listDiaries(diaryEntries);
    }

    /**
     * Exports statistics from each author with the amount of {@link DiaryEntry} entries they have written.
     */
    public static void exportAuthorStatistics() {
        List<Author> authors = RegisterHandler.getAuthorRegister().getAuthors();

        System.out.println("Number of recipes written by each author:");
        System.out.println("-----------------------------------------");

        for (Author author : authors) {
            List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(author);
            int diaryCount = diaryEntries.size();
            System.out.println(author.toString() + ": " + diaryCount);
        }
    }

    /**
     * Lists all diaries in the given list.
     * @param diaryEntries a list of {@link DiaryEntry} objects.
     */
    private static void listDiaries(List<DiaryEntry> diaryEntries) {
        System.out.println("=== Recipe Entries ===");
        for (int i = 0; i < diaryEntries.size(); i++) {
            System.out.println("#" + (i + 1) + " - " + diaryEntries.get(i));
        }
    }

}
