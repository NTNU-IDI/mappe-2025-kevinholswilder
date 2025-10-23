package main.java.edu.ntnu.iir.bidata.register;

/**
 *
 * DatabaseHandler class that handles every database interaction.
 *
 * @Author Kevin Holswilder
 * @Date 16.10.2025
 */

public class RegisterHandler {

    /**
     * Returns the diary database instance.
     * @return DiaryDatabase
     */
    public static DiaryRegister getDiaryDatabase() {
        return DiaryRegister.getInstance();
    }

    /**
     * Returns the author database instance.
     * @return AuthorDatabase
     */
    public static AuthorRegister getAuthorDatabase() {
        return AuthorRegister.getInstance();
    }

}
