package main.java.edu.ntnu.iir.bidata.service;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;

import java.util.List;

/**
 * <p>
 *     This singleton provides methods for interacting with the {@link DiaryEntry} register.
 *     </br>
 *     This singleton is responsible for handling all interactions between the UI and the register.
 * </p>
 *
 * This singleton allows users the following actions:
 * <ul>
 *     <li>Create a new {@link DiaryEntry}.</li>
 *     <li>Delete an existing {@link DiaryEntry}.</li>
 *     <li>Edit the line of a {@link DiaryEntry} its content.</li>
 *     <li>Manage the {@link RecipeLabel}s of a {@link DiaryEntry} object</li>
 * </ul>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 * @see <a href="https://www.geeksforgeeks.org/java/singleton-class-java/">Singleton Method Design Pattern in Java</a>
 */

public class DiaryService {

    private static DiaryService instance;

    /**
     * Private constructor to prevent instantiation.
     */
    private DiaryService() {}

    /**
     * @return The single instance of the {@link DiaryService} class.
     */
    public static DiaryService getInstance() {
        if (instance == null) {
            instance = new DiaryService();
        }
        return instance;
    }

    /**
     * Creates a new {@link DiaryEntry} object with the given title, content, author and labels.
     *
     * @param title The title of the diary entry.
     * @param content The content of the diary entry.
     * @param author The author of the diary entry.
     * @param labels The labels of the diary entry.
     * @return A new {@link DiaryEntry} object.
     */
    public DiaryEntry createDiaryEntry(String title, String content, Author author, List<RecipeLabel> labels) {
        DiaryEntry diaryEntry = new DiaryEntry(title, content, author);
        if (labels != null && !labels.isEmpty()) {
            for (RecipeLabel label : labels) {
                diaryEntry.addRecipeLabel(label);
            }
        }
        RegisterHandler.getDiaryRegister().addDiaryEntry(diaryEntry);
        return diaryEntry;
    }

    /**
     * Deletes a {@link DiaryEntry} object from the register.
     *
     * @param title The title of the diary entry.
     * @param author The author of the diary entry.
     * @return True if the diary entry was deleted, false otherwise.
     */
    public boolean deleteDiaryEntry(String title, Author author) {
        DiaryEntry diaryEntry = RegisterHandler.getDiaryRegister().getDiaryEntryByTitleAndAuthor(title, author);
        if (diaryEntry != null) {
            RegisterHandler.getDiaryRegister().removeDiaryEntry(diaryEntry.getId());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edits the content of a specific line of a {@link DiaryEntry}.
     *
     * @param entry The {@link DiaryEntry} to edit.
     * @param lineNumber The line number to edit.
     * @param content The new content of the line.
     */
    public void editContentLine(DiaryEntry entry, int lineNumber, String content) {
        entry.setContentLine(lineNumber, content);
    }

    /**
     * Adds a {@link RecipeLabel} to a {@link DiaryEntry}.
     *
     * @param entry The {@link DiaryEntry} to add the label to.
     * @param label The {@link RecipeLabel} to add.
     * @return True if the label was added successfully, false otherwise.
     */
    public boolean addLabel(DiaryEntry entry, RecipeLabel label) {
        if (entry.getRecipeLabels().contains(label)) {
            return false;
        }
        entry.addRecipeLabel(label);
        return true;
    }

    /**
     * Removes a {@link RecipeLabel} from a {@link DiaryEntry}.
     *
     * @param entry The {@link DiaryEntry} to remove the label from.
     * @param label The {@link RecipeLabel} to remove.
     * @return True if the label was removed successfully, false otherwise.
     */
    public boolean removeLabel(DiaryEntry entry, RecipeLabel label) {
        if (!entry.getRecipeLabels().contains(label)) {
            return false;
        }
        entry.removeRecipeLabel(label);
        return true;
    }

}
