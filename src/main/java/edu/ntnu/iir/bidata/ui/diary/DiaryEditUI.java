package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.service.DiaryService;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     This class provides methods for editing the properties of {@link DiaryEntry} objects.
 * </p>
 *
 * This class allows users to perform the following actions:
 * <ul>
 *     <li>Add a {@link RecipeLabel} to an existing {@link DiaryEntry}.</li>
 *     <li>Remove an existing {@link RecipeLabel} from a {@link DiaryEntry}.</li>
 *     <li>Edit individual lines of a {@link DiaryEntry} its content.</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiaryEditUI {

    /**
     * <p>
     *     Prompts the user to enter a title, which adds a {@link RecipeLabel} to an existing {@link DiaryEntry}.
     * </p>
     *
     * If successful, calls to {@link DiaryService#addLabel(DiaryEntry, RecipeLabel)}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void addLabelToDiary(Scanner input) {
        // Check if the user has any written diaries.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
        if (diaryEntries.isEmpty()) {
            System.out.println("You have not written any recipes yet, please write a recipe first before adding a label.");
            return;
        }

        // Print all existing titles for user experience.
        System.out.println("What is the title of the recipe you wish to add a label to?");
        DiaryHelper.printDiaryTitles(diaryEntries);

        // Check if the diary entry exists for the user.
        String title = DiaryHelper.getValidDiaryTitle(input);

        // Get the diary entry.
        DiaryEntry diaryEntry = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser());

        // Check if the diary entry already contains all available labels.
        if (diaryEntry.getRecipeLabels().size() == RecipeLabel.values().length) {
            System.out.println("This recipe contains all available labels.");
            return;
        }

        // Print out all labels, excluding those already added to the diary entry.
        System.out.println("What is the label you wish to add to this recipe?");
        System.out.println("Available Labels (excluding those already added):");
        EnumSet<RecipeLabel> missingLabels = EnumSet.complementOf(
                diaryEntry.getRecipeLabels().isEmpty()
                        ? EnumSet.noneOf(RecipeLabel.class)
                        : EnumSet.copyOf(diaryEntry.getRecipeLabels())
        );

        // Print all missing labels.
        RecipeLabel.printLabels(missingLabels);

        // Get the label from the user.
        RecipeLabel label = RecipeLabel.tryParse(input.nextLine());
        if (label == null) {
            System.out.println("Invalid label, please try again.");
            return;
        }

        // If true, the label was successfully added to the diary entry, else it already existed.
        boolean labelAdded = DiaryService.getInstance().addLabel(diaryEntry, label);
        if (labelAdded) {
            System.out.println("Successfully added label " + label.formattedLabel() + " to " + diaryEntry.getTitle() + ".");
        } else {
            System.out.println("This entry already contains this label, please try another label.");
        }
    }

    /**
     * <p>
     *     Prompts the user to enter a title, which removes an existing {@link RecipeLabel} from a {@link DiaryEntry}.
     * </p>
     *
     * If successful, calls to {@link DiaryService#removeLabel(DiaryEntry, RecipeLabel)}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void removeLabelFromDiary(Scanner input) {
        // Check if the user has any written diaries.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
        if (diaryEntries.isEmpty()) {
            System.out.println("You have not written any recipes yet, please write a recipe first before removing a label.");
            return;
        }

        // Print all existing titles for user experience.
        System.out.println("What is the title of the recipe you wish to remove a label from?");
        DiaryHelper.printDiaryTitles(diaryEntries);

        // Check if the diary entry exists for the user.
        String title = DiaryHelper.getValidDiaryTitle(input);

        // Get the diary entry.
        DiaryEntry diaryEntry = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser());

        // Check if the diary entry has any labels.
        if (diaryEntry.getRecipeLabels().isEmpty()) {
            System.out.println("This recipe does not contain any labels, try adding a label first.");
            return;
        }

        // Check if the diary entry has any labels.
        System.out.println("What is the label you wish to remove from this recipe?");
        System.out.println("Available Labels:");
        // Print all labels.
        RecipeLabel.printLabels(diaryEntry.getRecipeLabels());

        // Get the label from the user.
        RecipeLabel label = RecipeLabel.tryParse(input.nextLine());
        if (label == null) {
            System.out.println("Invalid label, please try again.");
            return;
        }

        // If true, the label was successfully removed from the diary entry, else it did not exist.
        boolean labelExists = DiaryService.getInstance().removeLabel(diaryEntry, label);
        if (labelExists) {
            System.out.println("Successfully removed label " + label.formattedLabel() + " from " + diaryEntry.getTitle() + ".");
        } else {
            System.out.println("This entry does not contain this label, please try another label.");
        }
    }

    /**
     * <p>
     *     Prompts the user to enter a title and a specific line number, which changes the content of a {@link DiaryEntry}.
     * </p>
     *
     * If successful, calls to {@link DiaryService#editContentLine(DiaryEntry, int, String)}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void editContentLine(Scanner input) {
        // Check if the user has any diaries.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
        if (diaryEntries.isEmpty()) {
            System.out.println("You have not written any recipes yet, please write a recipe first before removing a label.");
            return;
        }

        // Print all existing titles for user experience.
        System.out.println("What is the title of the recipe you wish to change the content of?");
        DiaryHelper.printDiaryTitles(diaryEntries);

        // Check if the diary entry exists for the user.
        String title = DiaryHelper.getValidDiaryTitle(input);

        // Get the diary entry.
        System.out.println("Which line of the content would you like to change?");
        DiaryEntry diaryEntry = RegisterHandler.getDiaryRegister().getDiaryEntriesByTitleAndAuthor(title, UserHandler.getCurrentUser());
        String[] contentLines = diaryEntry.getContent().split("\n");
        for (int i = 0; i < contentLines.length; i++) {
            System.out.println(i+1 + ". " + contentLines[i]);
        }

        // Get the line number from the user.
        System.out.println("Enter the line number:");
        int lineNumber = UtilityManager.ensureValidInteger(input);

        // Check if the line number is valid.
        if (lineNumber < 1 || lineNumber > contentLines.length) {
            System.out.println("Invalid line number, please try again.");
            return;
        }

        // Get the new content line.
        System.out.println("Enter the new line:");
        String newContent = UtilityManager.ensureNonEmptyTrimmedString(input);

        // Edit the content line.
        DiaryService.getInstance().editContentLine(diaryEntry, lineNumber, newContent);
        System.out.println("Successfully changed line " + lineNumber + " of " + diaryEntry.getTitle() + " to: " + newContent);
    }

}
