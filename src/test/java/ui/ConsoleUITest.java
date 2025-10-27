package test.java.ui;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI for testing.
 *
 * @Author Kevin Holswilder
 * @Date 27/10/2025
 */

public class ConsoleUITest {

    private Scanner scanner;

    /**
     * Initializes the console UI.
     * @throws IllegalStateException if the scanner cannot be initialized.
     */
    public void init() {
        try {
            scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.println("Initializing...");
        } catch (Exception e) {
            throw new IllegalStateException("Scanner could not be initialized.", e.getCause());
        }
    }

    /**
     * Starts the console UI.
     */
    public void start() {
        if (this.scanner == null) {
            this.init();
        }
        System.out.println("Welcome to the cooking diary!");

        // Positive tests
        Author author = new Author("kevinholswilder", "Kevin", "Holswilder");
        RegisterHandler.getAuthorDatabase().addAuthor(author);

        DiaryEntry diaryEntry = new DiaryEntry("Taco", "[...]");
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry);

        // Negative tests
        Author author2 = new Author("", "Vedbjørn", "Johansen");
        RegisterHandler.getAuthorDatabase().addAuthor(author2);

        DiaryEntry diaryEntry2 = new DiaryEntry("", "");
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry2);

        List<Author> authors = RegisterHandler.getAuthorDatabase().getAuthors();
        List<DiaryEntry> diaryEntries = RegisterHandler.getDiaryDatabase().getDiaryEntries();

        for (Author it : authors) {
            System.out.println(it.toString());
        }

        for (DiaryEntry it : diaryEntries) {
            System.out.println(it.toString());
        }
    }

}