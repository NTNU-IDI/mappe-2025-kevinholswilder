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

        Assert.assertSame(1, authors.size());
        Assert.assertSame("kevinholswilder", authors.getFirst().getUsername());
    }

    @Test
    public void addDiaryEntryToRegister() {
        DiaryEntry diaryEntry = new DiaryEntry("Taco", "1x Minced meat, 1x Cheese, 1x Sauce");
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry);

        DiaryEntry diaryEntry1 = new DiaryEntry("", "");
        RegisterHandler.getDiaryDatabase().addDiaryEntry(diaryEntry1);

        List<DiaryEntry> diaries = RegisterHandler.getDiaryDatabase().getDiaryEntries();

        Assert.assertSame(1, diaries.size()); // Expected 2
        Assert.assertEquals("Taco", RegisterHandler.getDiaryDatabase().getDiaryEntryByTitle("Taco").getTitle());
    }

    @Test
    public void startTests() {
        this.addAuthorsToRegister();
        this.addDiaryEntryToRegister();
    }

}