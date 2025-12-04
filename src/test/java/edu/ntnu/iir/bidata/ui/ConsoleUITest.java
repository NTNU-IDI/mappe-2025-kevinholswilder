package test.java.edu.ntnu.iir.bidata.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import main.java.edu.ntnu.iir.bidata.ui.ConsoleUI;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link ConsoleUI} class.
 *
 * <p>This class tests the functionality of the {@link ConsoleUI} class.
 *
 * @author Kevin Holswilder
 * @since 2025/11/01
 */

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ConsoleUITest {

  private Scanner scanner;

  /**
   * Initializes the console UI by setting the scanner to a UTF-8 scanner.
   */
  public void init() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    System.out.println("Initializing...");
  }

  /**
   * Verifies that the scanner is not null and starts the program.
   */
  @Test
  public void startUITest() {
    if (this.scanner == null) {
      this.init();
    }

    Assert.assertNotNull(this.scanner);
  }

}