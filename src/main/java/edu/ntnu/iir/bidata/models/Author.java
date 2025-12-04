package main.java.edu.ntnu.iir.bidata.models;

import java.util.UUID;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;


/**
 * Represents an author with a username, name and surname.
 * Additionally, this class provides getter methods to access the author's attributes.
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 *     Author author = new Author("johndoe", "John", "Doe");
 *     System.out.println(author.getName()); // John Doe
 * </pre>
 *
 * @author Kevin Holswilder
 * @since 2025/10/12
 */

public class Author {

  private final String username;
  private final String name;
  private final String surname;

  /**
   * Creates a new {@link Author} object, given the username, name and surname.
   * The author's id is generated randomly using a {@link UUID}.
   *
   * @param username Sets the username of an author.
   * @param name     Sets the name of an author.
   * @param surname  Sets the surname of an author.
   */
  public Author(String username, String name, String surname) {
    this.username = username;
    this.name = name;
    this.surname = surname;
  }

  /**
   * Returns the name of an author.
   *
   * @return the name of an author.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the surname of an author.
   *
   * @return the surname of an author.
   */
  public String getSurname() {
    return this.surname;
  }

  /**
   * Returns the username of an author.
   *
   * @return the username of an author.
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Returns a formatted string representation of an author,
   * where the name and surname are capitalized.
   *
   * @return a string in the format of "Name Surname".
   */
  @Override
  public String toString() {
    return UtilityManager.capitalize(this.name) + " " + UtilityManager.capitalize(this.surname);
  }

}
