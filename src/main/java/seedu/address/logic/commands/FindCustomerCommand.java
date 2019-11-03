package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CustomerNameContainsStringPredicate;

/**
 * Finds and lists all customers in address book whose name contains the phrase.
 * Keyword matching is case insensitive.
 */
public class FindCustomerCommand extends Command {

    public static final String COMMAND_WORD = "findC";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers whose names contain "
            + "the phrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PHRASE [MORE PHRASE]...\n"
            + "Example: " + COMMAND_WORD + " alex ch";

    private final CustomerNameContainsStringPredicate predicate;

    public FindCustomerCommand(CustomerNameContainsStringPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCustomerCommand // instanceof handles nulls
                && predicate.equals(((FindCustomerCommand) other).predicate)); // state check
    }
}
