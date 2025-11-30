package main.java.edu.ntnu.iir.bidata;

import main.java.edu.ntnu.iir.bidata.ui.ConsoleUI;

/**
 * Entry point of the program.
 *
 * <p>
 *     This class contains the {@code main} method which initializes the console user interface,
 *     and starts the program.
 * </p>
 *
 * @Author Kevin Holswilder
 * @Date 2025/10/12
 */

public class Main {

    /**
     * Launches the program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.init();
        consoleUI.start();
    }

}