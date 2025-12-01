package main.java.edu.ntnu.iir.bidata.ui;

/**
 * <p>
 *     Provides constants for interaction keys.
 * </p>
 *
 * @author Kevin Holswilder
 * @since 2025/12/01
 */

public class InteractionKeys {

    /**
     * Private constructor to prevent instantiation.
     */
    private InteractionKeys() {}

    // Shared constants
    public static final int INITIAL_VALUE = -1;

    /**
     * Set of interaction keys for the start flow of the program.
     */
    public static final int LOGIN = 1;
    public static final int REGISTER = 2;
    public static final int EXIT_PROGRAM = 3;

    /**
     * Set of interaction keys for the diary flow of the program.
     */
    public static final int WRITE_DIARY = 1;
    public static final int DELETE_DIARY = 2;
    public static final int RUN_EDIT_FLOW = 3;
    public static final int RUN_SEARCH_FLOW = 4;
    public static final int EXPORT_DIARIES = 5;
    public static final int EXPORT_AUTHOR_STATISTICS = 6;
    public static final int LOGOUT = 7;

    /**
     * Set of interaction keys for the edit flow of the program.
     */
    public static final int EDIT_ADD_LABEL = 1;
    public static final int EDIT_REMOVE_LABEL = 2;
    public static final int EDIT_CONTENT_LINE = 3;
    public static final int EDIT_RETURN = 4;

    /**
     * Set of interaction keys for the search flow of the program.
     */
    public static final int SEARCH_TITLE = 1;
    public static final int SEARCH_DATE = 2;
    public static final int SEARCH_PERIOD = 3;
    public static final int SEARCH_AUTHOR = 4;
    public static final int SEARCH_PROMPT = 5;
    public static final int SEARCH_LABEL = 6;
    public static final int SEARCH_RETURN = 7;

}
