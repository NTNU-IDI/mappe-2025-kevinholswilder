package main.java.edu.ntnu.iir.bidata.register;

/**
 * RegisterHandler class that provides access to the local register instances.
 *
 * @author Kevin Holswilder
 * @since 2025/10/16
 */

public class RegisterHandler {

  /**
   * Private constructor to prevent instantiation.
   */
  private RegisterHandler() {
  }

  /**
   * Provides access to the diary register instance.
   *
   * @return the {@link DiaryRegister} instance.
   */
  public static DiaryRegister getDiaryRegister() {
    return DiaryRegister.getInstance();
  }

  /**
   * Provides access to the author register instance.
   *
   * @return the {@link AuthorRegister} instance.
   */
  public static AuthorRegister getAuthorRegister() {
    return AuthorRegister.getInstance();
  }

}
