package test.java.models;

import main.java.edu.ntnu.iir.bidata.enumerations.RecipeLabel;
import main.java.edu.ntnu.iir.bidata.models.Author;
import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import org.junit.Assert;
import org.junit.Test;

/**
 * DiaryEntry Test Class
 *
 * @Author Kevin Holswilder
 * @Date 25/11/2025
 */

public class DiaryEntryTest {

    @Test
    public void testDiaryEntry() {
        Author author = new Author("supermario", "Mario", "Mario");
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

    public void startDiaryEntryTest() {
        this.testDiaryEntry();
    }

}
