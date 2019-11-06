package seedu.address.commons.core;

import seedu.address.logic.GlobalClock;
import seedu.address.model.task.Task;

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
    public static final String MESSAGE_ASSIGN_SUCCESS = "Assigned #%1$d to %2$s at %3$s";
    public static final String MESSAGE_ALREADY_ASSIGNED = "This task is already scheduled. ";
    public static final String MESSAGE_NOT_TODAY = "The task is not scheduled for today. " + "\n"
            + String.format("Only tasks scheduled for today can be assigned. Today is %s.",
            GlobalClock.dateToday().format(Task.DATE_FORMAT_FOR_PRINT));
}
