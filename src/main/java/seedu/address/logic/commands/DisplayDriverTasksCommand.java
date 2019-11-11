package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DriverIsTheSamePredicate;

/**
 * Display all the tasks assigned to the driver given by the input index.
 * This command is a follow up search after the FindDriverCommand.
 */
public class DisplayDriverTasksCommand extends Command {

    public static final String COMMAND_WORD = "driver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all the tasks assigned to the driver given by "
            + "the specified index and displays them as a list with index numbers.\n"
            + "Parameters: [INDEX NUMBER]\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final DriverIsTheSamePredicate predicate;

    public DisplayDriverTasksCommand(DriverIsTheSamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}
