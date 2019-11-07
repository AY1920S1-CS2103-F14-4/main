package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class GoCommand extends Command {

    public static final String COMMAND_WORD = "go";

    public static final String MESSAGE_GO_SUCCESS = "Navigated to %s tab!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigates to the specified tab bar "
            + "the specified keywords (case-sensitive) "
            + "\n"
            + "Parameters: TAB_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " history";

    private String tabType;

    public GoCommand(String tabType) {
        this.tabType = tabType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_GO_SUCCESS, tabType), false, false, tabType);
    }

}
