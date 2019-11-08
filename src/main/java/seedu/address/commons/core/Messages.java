package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX = "The customer index provided is invalid";
    public static final String MESSAGE_INVALID_DRIVER_DISPLAYED_INDEX = "The driver index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_CUSTOMERS_LISTED_OVERVIEW = "%1$d customers listed!";
    public static final String MESSAGE_DRIVERS_LISTED_OVERVIEW = "%1$d drivers listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_INVALID_UNDO_REQUEST = "The programme is at its initial state, no commands "
            + "available to be undone";
    public static final String MESSAGE_INVALID_REDO_REQUEST = "The programme is at its latest state, no commands "
            + "available to be redone";
}
