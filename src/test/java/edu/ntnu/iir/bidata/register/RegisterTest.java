package test.java.edu.ntnu.iir.bidata.register;

import java.util.List;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.register.RegisterHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link RegisterHandler} class.
 *
 * <p>This class tests the functionality of the {@link RegisterHandler} class.
 *
 * @author Kevin Holswilder
 * @since 2025/10/28
 */

public class RegisterTest {

  /**
   * Tests the functionality of the {@link RegisterHandler#getAuthorRegister()} instance,
   * by adding an author to the register and verifying that the author is added correctly.
   */
  @Test
  public void addAuthorsToRegister() {
    Author author = new Author("kevinholswilder", "Kevin", "Holswilder");
    RegisterHandler.getAuthorRegister().addAuthor(author);

    List<Author> authors = RegisterHandler.getAuthorRegister().getAuthors();

    // Positive tests
    Assert.assertEquals(1, authors.size());
    Assert.assertEquals("kevinholswilder", authors.getFirst().getUsername());

    // Negative tests
    Assert.assertNotEquals("username", authors.getFirst().getUsername());
    Assert.assertFalse(authors.stream().anyMatch(a -> a.getUsername().equals("username")));
  }

  /**
   * Tests the functionality of the {@link RegisterHandler#getDiaryRegister()} instance,
   * by creating a test {@link Author} and adding two separate {@link DiaryEntry} objects
   * to the register and verifying that the objects are added correctly.
   */
  @Test
  public void addDiaryEntryToRegister() {
    Author testAuthor = new Author("username", "John", "Doe");

    DiaryEntry diaryEntry =
        new DiaryEntry("Taco", "1x Minced meat, 1x Cheese, 1x Sauce", testAuthor);
    RegisterHandler.getDiaryRegister().addDiaryEntry(diaryEntry);

    DiaryEntry diaryEntry1 =
        new DiaryEntry("Sushi", "1x Salmon, 1x Vinegar, 1x Rice, 1x Soy sauce", testAuthor);
    RegisterHandler.getDiaryRegister().addDiaryEntry(diaryEntry1);

    List<DiaryEntry> diaries = RegisterHandler.getDiaryRegister().getDiaryEntries();

    // Positive tests
    Assert.assertEquals(2, diaries.size()); // Expected 2
    Assert.assertEquals("Taco",
        RegisterHandler.getDiaryRegister().getDiaryEntryByTitleAndAuthor("Taco", testAuthor)
            .getTitle());

    // Negative tests
    Assert.assertNotNull(
        RegisterHandler.getDiaryRegister().getDiaryEntryByTitleAndAuthor("Sushi", testAuthor));
  }

  /**
   * Start the register test.
   */
  public void startRegisterTest() {
    this.addAuthorsToRegister();
    this.addDiaryEntryToRegister();
  }

}