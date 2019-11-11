package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REDO_REQUEST;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redo the most recent changes to the state of the (@code Customer), (@code Driver) or (@code Task)
 * data.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " redo changes";

    public static final String MESSAGE_SUCCESS = "Redo successful! ";

    public RedoCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoCentralManager()) {
            throw new CommandException(MESSAGE_INVALID_REDO_REQUEST);
        } else {
            model.redoCentralManager();
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
