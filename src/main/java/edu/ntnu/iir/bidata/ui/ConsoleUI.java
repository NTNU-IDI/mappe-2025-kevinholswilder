package main.java.edu.ntnu.iir.bidata.ui;

import main.java.edu.ntnu.iir.bidata.flow.FlowHandler;

import java.nio.charset.StandardCharsets;
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
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Initializing...");
    }

    /**
     * Starts the console UI.
     */
    public void start() {
        if (this.scanner == null) {
            this.init();
        }
        FlowHandler.startFlow(this.scanner);
    }

}