package main.java.edu.ntnu.iir.bidata.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import main.java.edu.ntnu.iir.bidata.flow.FlowHandler;

/**
 * This class provides a simple UI for interacting with the program.
 *
 * @author Kevin Holswilder
 * @since 2025/10/12
 */

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ConsoleUI {

  private Scanner scanner;

  /**
   * Initializes the console UI by creating a UTF-8 scanner for reading input.
   *
   * <p>This method is furthermore automatically called by {@link #start()} if the scanner is null.
   */
  public void init() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    System.out.println("Initializing program...");
  }

  /**
   * Starts the program and begins the flow of the program.
   */
  public void start() {
    if (this.scanner == null) {
      this.init();
    }
    FlowHandler.startFlow(this.scanner);
  }

}