package test.java.ui;

import org.junit.Assert;
import org.junit.Test;
import test.java.flow.FlowHandler;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Console UI Test class
 *
 * @Author Kevin Holswilder
 * @Date 11/01/2025
 */

public class ConsoleUITest {

    private Scanner scanner;

    /**
     * Initializes the console UI.
     */
    public void init() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Initializing...");
    }

    /**
     * Starts the console UI.
     */
    @Test
    public void start() {
        if (this.scanner == null) {
            this.init();
        }

        Assert.assertNotNull(this.scanner);

        System.out.println("Welcome to the cooking diary!");
        FlowHandler.startFlow(this.scanner);
    }

}