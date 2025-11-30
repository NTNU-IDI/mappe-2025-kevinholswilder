package main.java.edu.ntnu.iir.bidata.enumerations;

import main.java.edu.ntnu.iir.bidata.models.DiaryEntry;
import main.java.edu.ntnu.iir.bidata.utils.UtilityManager;

import java.util.Collection;

/**
 * This class contains all the possible labels for the labels of a {@link DiaryEntry}.
 *
 * @Author Kevin Holswilder
 * @Date 2025/11/25
 */

public enum RecipeLabel {
    VEGETARIAN,
    MILKLESS,
    VEGAN,
    GLUTENFREE,
    HALAL,
    KETO,
    EGGFREE,
    SOYFREE;

    /**
     * Returns null if the input is not a valid label.
     *
     * @param input the user input.
     * @return the {@link RecipeLabel} if valid, null otherwise.
     */
    public static RecipeLabel tryParse(String input) {
        try {
            return valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Prints all the labels in the collection.
     *
     * @param labels the collection of labels.
     */
    public static void printLabels(Collection<RecipeLabel> labels) {
        for (RecipeLabel label : labels) {
            System.out.println(" - " + label.formattedLabel());
        }
    }

    /**
     * Returns the label in a formatted way.
     *
     * @return the formatted label.
     */
    public String formattedLabel() {
        return UtilityManager.capitalize(this.name().toLowerCase());
    }

}
