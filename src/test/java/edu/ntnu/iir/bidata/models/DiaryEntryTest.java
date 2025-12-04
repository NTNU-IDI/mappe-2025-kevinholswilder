package test.java.edu.ntnu.iir.bidata.models;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link DiaryEntry} class.
 *
 * <p>This class tests the functionality of the {@link DiaryEntry} class.
 *
 * @author Kevin Holswilder
 * @since 2025/11/25
 */

public class DiaryEntryTest {

  /**
   * Tests the functionality of the {@link DiaryEntry} class,
   * and verifies that the attributes are set correctly.
   */
  @Test
  public void testDiaryEntry() {
    Author author = new Author("supermario", "Mario", "Luigi");
    DiaryEntry diaryEntry = new DiaryEntry("Pizza", "1x Cheese, 1x Tomatoes, 1x Pepperoni", author);

    // Add label
    diaryEntry.addRecipeLabel(RecipeLabel.GLUTENFREE);

    // Positive tests
    Assert.assertEquals("Pizza", diaryEntry.getTitle());
    Assert.assertEquals("1x Cheese, 1x Tomatoes, 1x Pepperoni", diaryEntry.getContent());
    Assert.assertTrue(diaryEntry.getRecipeLabels().contains(RecipeLabel.GLUTENFREE));

    // Negative tests
    Assert.assertFalse(diaryEntry.getRecipeLabels().contains(RecipeLabel.VEGAN));
  }

  /**
   * Starts the diary entry test.
   */
  public void startDiaryEntryTest() {
    this.testDiaryEntry();
  }

}
