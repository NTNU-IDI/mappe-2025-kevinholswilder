package main.java.edu.ntnu.iir.bidata;

import main.java.edu.ntnu.iir.bidata.ui.ConsoleUI;

/**
 * Main class
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

public class Main {

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.init();
        consoleUI.start();
    }
}