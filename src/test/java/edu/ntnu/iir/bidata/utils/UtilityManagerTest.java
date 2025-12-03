package test.java.edu.ntnu.iir.bidata.utils;

import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link UtilityManager} class.
 *
 * <p>
 *     This class tests some of the functionality that is being used in the {@link UtilityManager} class.
 * </p>
 * <i>Note: Not all functionalities are being tested.</i>
 *
 * @author Kevin Holswilder
 * @since 2025/10/28
 */

public class UtilityManagerTest {

    /**
     * Checks if a string is alphabetic.
     *
     * @param string the string to be checked.
     * @return true if the string is alphabetic, false otherwise.
     */
    private boolean isAlphabetic(String string) {
        return string.chars().allMatch(Character::isAlphabetic);
    }

    /**
     * Tests the functionality of ensuring a string solely consists of alphabetic characters.
     */
    @Test
    public void ensureAlphabeticStringTest() {
        // Positive tests
        Assert.assertTrue(this.isAlphabetic("thisisatest"));

        // Negative tests
        Assert.assertFalse(this.isAlphabetic("1234567890"));
        Assert.assertFalse(this.isAlphabetic("john doe"));
    }

    /**
     * Start the utility manager test.
     */
    public void startUtilityManagerTest() {
        this.ensureAlphabeticStringTest();
    }

}
