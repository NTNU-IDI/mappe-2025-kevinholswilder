package main.java.edu.ntnu.iir.bidata.ui.diary;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.DiaryRegister;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.service.DiaryService;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     This class provides the method for deleting existing {@link DiaryEntry} objects from the {@link DiaryRegister}.
 * </p>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class DiaryDeleteUI {

    /**
     * <p>
     *     Prompts the user to enter the title of the {@link DiaryEntry} they wish to delete from themselves.
     * </p>
     *
     * If a {@link DiaryEntry} is found for the {@link Author}, it proceeds to call {@link DiaryService#deleteDiaryEntry(String, Author)}.
     *
     * @param input a {@link Scanner} object to read user input.
     */
    public void deleteDiary(Scanner input) {
        // Check if the user has any written diaries.
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryRegister().getDiaryEntriesByAuthor(UserHandler.getCurrentUser());
        if (diaryEntries.isEmpty()) {
            System.out.println("You have not written any recipes yet, please write a recipe first before attempting to delete one.");
            return;
        }

        // Print all existing titles for user experience.
        System.out.println("Enter the title of your recipe diary you're trying to delete:");
        DiaryHelper.printDiaryTitles(diaryEntries);

        // Get the title from the user.
        String title = UtilityManager.ensureNonEmptyTrimmedString(input);

        // If true is returned, the diary entry was successfully deleted, else it was not found.
        boolean diaryExists = DiaryService.getInstance().deleteDiaryEntry(title, UserHandler.getCurrentUser());
        if (diaryExists) {
            System.out.println("Recipe diary with title " + title + " was successfully deleted.");
        } else {
            System.out.println("No entry was found.");
        }
    }

}
