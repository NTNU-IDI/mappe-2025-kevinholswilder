package test.java.utils;

import org.junit.Assert;
import org.junit.Test;

public class UtilityManagerTest {

    @Test
    public void ensureAlphabeticStringTest() {
        String string = "Thisisatest";

        boolean isAlphabetic = string.chars().allMatch(Character::isAlphabetic);

        Assert.assertTrue(isAlphabetic);
    }

    public void startUtilityManagerTest() {
        this.ensureAlphabeticStringTest();
    }

}
