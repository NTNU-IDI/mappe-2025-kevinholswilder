package test.java.register;

import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * RegisterHandler Test Class
 *
 * @Author Kevin Holswilder
 * @Date 28/10/2025
 */

public class RegisterTest {

    @Test
    public void addAuthorsToRegister() {
        Author author = new Author("kevinholswilder", "Kevin", "Holswilder");
        RegisterHandler.getAuthorDatabase().addAuthor(author);

        List<Author> authors = RegisterHandler.getAuthorDatabase().getAuthors();

        // Positive tests
        Assert.assertEquals(1, authors.size());
        Assert.assertEquals("kevinholswilder", authors.getFirst().getUsername());

        // Negative tests
        Assert.assertNotEquals("username", authors.getFirst().getUsername());
        Assert.assertFalse(authors.stream().anyMatch(a -> a.getUsername().equals("username")));
    }

    @Test
    public void addDiaryEntryToRegister() {
        Author testAuthor = new Author("username", "John", "Doe");

        DiaryEntry diaryEntry = new DiaryEntry("Taco", "1x Minced meat, 1x Cheese, 1x Sauce", testAuthor);
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry);

        DiaryEntry diaryEntry1 = new DiaryEntry("Sushi", "1x Salmon, 1x Vinegar, 1x Rice, 1x Soy sauce", testAuthor);
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry1);

        List<DiaryEntry> diaries = RegisterHandler.getDiaryDatabase().getDiaryEntries();

        // Positive tests
        Assert.assertEquals(2, diaries.size()); // Expected 2
        Assert.assertEquals("Taco", RegisterHandler.getDiaryDatabase().getDiaryEntriesByTitleAndAuthor("Taco", testAuthor).getTitle());

        // Negative tests
        Assert.assertNotNull(RegisterHandler.getDiaryDatabase().getDiaryEntriesByTitleAndAuthor("Sushi", testAuthor));
    }

    public void startRegisterTest() {
        this.addAuthorsToRegister();
        this.addDiaryEntryToRegister();
    }

}