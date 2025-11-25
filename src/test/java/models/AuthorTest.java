package test.java.models;

import main.java.edu.ntnu.iir.bidata.models.Author;
import org.junit.Assert;
import org.junit.Test;

public class AuthorTest {

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

    public void startAuthorTest() {
        this.testAuthor();
    }

}
