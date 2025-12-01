package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.DiaryRegister;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.service.DiaryService;
import main.java.edu.ntnu.iir.bidata.service.UserService;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     This class provides the method for creating {@link DiaryEntry} objects and adding them to the {@link DiaryRegister}.
 * </p>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiaryCreateUI {

    /**
     * <p>
     *     Prompts the user to enter a title which has to be unique for the {@link Author},
     *     write the content, and additionally add (optional) {@link RecipeLabel}s to the diary entry.
     * </p>
     *
     * If successful, calls to {@link DiaryService#createDiaryEntry(String, String, Author, List)}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void writeDiary(Scanner input) {
        // Ensures the user enters a title that doesn't already exist for this author.
        String title;
        while (true) {
            System.out.println("Enter the title of your recipe diary:");
            title = UtilityManager.ensureNonEmptyTrimmedString(input);

            boolean titleExists = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserService.getCurrentUser()) == null;
            if (!titleExists) {
                System.out.println("You already have a recipe with this title, please enter a new title.");
            } else {
                break;
            }
        }

        // Write the content of the diary entry.
        System.out.println("Write the content for your cooking diary:\nType 'exit' to save and finish writing.");
        String content = UtilityManager.readMultiLineInput(input);

        // Check if the content is empty.
        if (content.isBlank()) {
            System.out.println("You cannot write an empty recipe diary, please try again.");
            return;
        }

        // Get the current author.
        Author author = UserService.getCurrentUser();

        // Add (optional) labels to the diary entry.
        System.out.println("Enter labels for you recipe, one at a time.\nType 'exit' when you are finished or don't wish to add any labels.");
        System.out.println("Available Labels:");
        RecipeLabel.printLabels(EnumSet.allOf(RecipeLabel.class));

        // Create a new ArrayList to store labels.
        List<RecipeLabel> labels = new ArrayList<>();

        // Read labels from the user.
        List<String> labelsStrings = UtilityManager.readMultiLineInputList(input);
        for (String labelString : labelsStrings) {
            if (labelString.isEmpty()) {
                continue;
            }
            // Parse the string to a RecipeLabel object.
            RecipeLabel label = RecipeLabel.tryParse(labelString.trim());
            if (label == null) {
                System.out.println("Ignoring invalid label: " + labelString + ".");
                continue;
            }
            labels.add(label);
        }

        // Add the diary entry to the register.
        DiaryEntry diaryEntry = DiaryService.getInstance().createDiaryEntry(title, content, author, labels);
        System.out.println("Successfully created recipe diary with title: " + diaryEntry.getTitle() + ".");
    }

}
