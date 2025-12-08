package main.java.edu.ntnu.iir.bidata.register;

import java.util.HashMap;
import java.util.List;
import main.java.edu.ntnu.iir.bidata.models.Author;

/**
 * Represents the {@link Author} register.
 *
 * <p>This class is a singleton class that ensures there only exists 1 instance of
 * the local register at a time to ensure a single source of truth
 * throughout the runtime of the program.
 * </p>
 *
 * @author Kevin Holswilder
 * @see <a href="https://www.geeksforgeeks.org/java/singleton-class-java/">Singleton Method Design Pattern in Java</a>
 * @since 2025/10/21
 */

public class AuthorRegister {

  private static AuthorRegister instance;

  private final HashMap<String, Author> authorEntries;

  /**
   * Private constructor to prevent instantiation.
   */
  private AuthorRegister() {
    this.authorEntries = new HashMap<>();
  }

  /**
   * Returns the instance of the register.
   *
   * @return the single instance of the register.
   */
  static AuthorRegister getInstance() {
    if (instance == null) {
      instance = new AuthorRegister();
    }
    return instance;
  }

  /**
   * Returns a {@link Author} object where the input matches the author's username.
   *
   * @return a {@link Author} where the username match, if no match is found, returns null.
   */
  public Author getAuthorByUsername(String username) {
    return this.authorEntries.getOrDefault(username.toLowerCase(), null);
  }

  /**
   * Returns a {@link Author} object where the full name matches the author's first and last name.
   *
   * @param fullName takes in a string consisting of the author's full name.
   * @return a {@link Author} where the full name match, if no match is found, returns null.
   */
  public Author getAuthorByFullName(String fullName) {
    return this.getAuthors().stream()
        .filter(it -> it.toString().equalsIgnoreCase(fullName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Adds the given {@link Author} to the register.
   *
   * @param author adds an {@link Author} to the register.
   */
  public void addAuthor(Author author) {
    this.authorEntries.putIfAbsent(author.getUsername().toLowerCase(), author);
  }

  /**
   * Returns all {@link Author} objects in the register.
   *
   * @return A list of all {@link Author} objects in the register.
   */
  public List<Author> getAuthors() {
    return this.authorEntries.values().stream().toList();
  }

}
