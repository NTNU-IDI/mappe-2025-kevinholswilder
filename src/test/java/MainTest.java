package test.java;

import test.java.ui.ConsoleUITest;

/**
 * Main Class for testing.
 *
 * @Author Kevin Holswilder
 * @Date 27/10/2025
 */

public class MainTest {

    public static void main(String[] args) {
        ConsoleUITest consoleUITest = new ConsoleUITest();
        consoleUITest.init();
        consoleUITest.start();
    }

}
