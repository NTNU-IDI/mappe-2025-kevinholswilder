package test.java;

import test.java.register.RegisterTest;
import test.java.utils.UtilityManagerTest;

/**
 * Main Class for testing.
 *
 * @Author Kevin Holswilder
 * @Date 27/10/2025
 */

public class MainTest {

    public static void main(String[] args) {
        RegisterTest registerTest = new RegisterTest();
        registerTest.startTests();

        UtilityManagerTest utilityManagerTest = new UtilityManagerTest();
        System.out.println("Enter a value to test the utility manager:");
        utilityManagerTest.readMultiLineInput();
    }

}
