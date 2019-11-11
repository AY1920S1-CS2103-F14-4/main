package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.DisplayDriverTasksCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.DriverNameContainsStringPredicate;

/**
 * Finds and lists all drivers in address book whose name contains the phrase.
 * Keyword matching is case insensitive.
 */
public class FindDriverCommand extends Command {

    public static final String COMMAND_WORD = "findD";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all drivers whose names contain "
            + "the specified phrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PHRASE [MORE PHRASE]\n"
            + "Example: " + COMMAND_WORD + " alex ch";

    private final DriverNameContainsStringPredicate predicate;

    public FindDriverCommand(DriverNameContainsStringPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDriverList(predicate);
        DisplayDriverTasksCommandParser temp = new DisplayDriverTasksCommandParser();
        temp.setDriverList(model.getFilteredDriverList());
        return new CommandResult(
                String.format(Messages.MESSAGE_DRIVERS_LISTED_OVERVIEW, model.getFilteredDriverList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDriverCommand // instanceof handles nulls
                && predicate.equals(((FindDriverCommand) other).predicate)); // state check
    }
}
