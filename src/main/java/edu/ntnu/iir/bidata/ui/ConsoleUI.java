package main.java.edu.ntnu.iir.bidata.ui;

import java.util.Scanner;

/**
 * Console UI
 *
 * @Author Kevin Holswilder
 * @Date 12/10/2025
 */

public class ConsoleUI {

    private Scanner scanner;

    /**
     * Initializes the console UI.
     * @throws IllegalStateException if the scanner cannot be initialized.
     */
    public void init() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Initializing...");
        } catch (Exception e) {
            throw new IllegalStateException("Scanner could not be initialized.", e.getCause());
        }
    }

    /**
     * Starts the console UI.
     */
    public void start() {
        System.out.println("Welcome to the cooking diary!");
    }

}