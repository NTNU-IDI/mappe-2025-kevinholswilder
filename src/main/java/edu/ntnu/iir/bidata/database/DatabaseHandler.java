package main.java.edu.ntnu.iir.bidata.database;

/**
 *
 * DatabaseHandler class that handles every database interaction.
 *
 * @Author Kevin Holswilder
 * @Date 16.10.2025
 */

public class DatabaseHandler {

    /**
     * Returns the diary database instance.
     * @return DiaryDatabase
     */
    public static DiaryDatabase getDiaryDatabase() {
        return DiaryDatabase.getInstance();
    }

    /**
     * Returns the author database instance.
     * @return AuthorDatabase
     */
    public static AuthorDatabase getAuthorDatabase() {
        return AuthorDatabase.getInstance();
    }

}
