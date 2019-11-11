package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskDescriptionContainsStringPredicate;

/**
 * Finds and lists all tasks in address book whose name contains the phrase.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose descriptions contain "
            + "the specified phrase (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PHRASE [MORE PHRASE]\n"
            + "Example: " + COMMAND_WORD + " Durex XL pack";

    private final TaskDescriptionContainsStringPredicate predicate;

    public FindTaskCommand(TaskDescriptionContainsStringPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }

}
