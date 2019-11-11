package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_UNDO_REQUEST;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the most recent user command that changes the state of the (@code Customer), (@code Driver) or (@code Task)
 * data.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " undo changes";

    public static final String MESSAGE_SUCCESS = "Undo successful! ";

    public UndoCommand() {}

    @Override
    public CommandResult execute (Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoCentralManager()) {
            throw new CommandException(MESSAGE_INVALID_UNDO_REQUEST);
        } else {
            model.undoCentralManager();
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
