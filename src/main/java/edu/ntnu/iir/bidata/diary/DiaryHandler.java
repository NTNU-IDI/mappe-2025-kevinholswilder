package main.java.edu.ntnu.iir.bidata.diary;

import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import main.java.edu.ntnu.iir.bidata.user.UserHandler;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.Scanner;

/**
 * Handles diary-related operations.
 *
 * @Author Kevin Holswilder
 * @Date 01.11.2025
 */

public class DiaryHandler {

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

}
