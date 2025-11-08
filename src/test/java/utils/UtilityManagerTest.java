package test.java.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class UtilityManagerTest {

    Scanner scanner = new Scanner(System.in);

    @Test
    public void readMultiLineInput() {
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            stringBuilder.append(line).append("\n");
        }
        Assert.assertEquals(0, stringBuilder.toString().length());
    }

    @Test
    public void ensureAlphabeticString() {
        String string = "Thisisatest";

        boolean isAlphabetic = string.chars().allMatch(Character::isAlphabetic);

        Assert.assertTrue(isAlphabetic);
    }

}
