package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * List delivered task for specified driver
 */
public class ViewDriverTasksCommand extends Command {

    public static final String COMMAND_WORD = "view driver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the delivered tasks of the specified driver "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "listed delivered tasks";

    private final Index targetIndex;

    public ViewDriverTasksCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> currentDriverList = model.getFilteredPersonList(); //change Person to Driver here
        if (targetIndex.getZeroBased() >= currentDriverList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } //change to invalid driver index

        Person driverToView = currentDriverList.get(targetIndex.getZeroBased());
        model.viewDriverTask(driverToView);
        return new CommandResult(String.format(MESSAGE_SUCCESS, driverToView));
    }

}
