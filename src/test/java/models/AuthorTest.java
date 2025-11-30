package test.java.models;

import main.java.edu.ntnu.iir.bidata.models.Author;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link Author} class.
 *
 * <p>
 *     This class tests the functionality of the {@link Author} class.
 * </p>
 *
 * @author Kevin Holswilder
 * @Date 2025/11/25
 */

public class AuthorTest {

    /**
     * Tests the creation of a {@link Author} object and verifies that the attributes are set correctly.
     */
    @Test
    public void testAuthor() {
        Author author = new Author("kevinholswilder", "Kevin", "Holswilder");

        // Positive tests
        Assert.assertEquals("kevinholswilder", author.getUsername());
        Assert.assertEquals("Kevin", author.getName());

        // Negative tests
        Assert.assertNotEquals("kevin", author.getUsername());
        Assert.assertNotNull(author.getName());
        Assert.assertNotNull(author.getSurname());
    }

    /**
     * Start the author test.
     */
    public void startAuthorTest() {
        this.testAuthor();
    }

}
