package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens a window displaying driver details.
 */
public class ViewDriverWindowCommand extends Command {

    public static final String COMMAND_WORD = "viewDW";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows window of driver details.\n"
            + "Parameters: "
            + "[DRIVER_ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + "3";

    public static final String SHOWING_VIEW_WINDOW = "Opened view driver window.";

    private int id;

    public ViewDriverWindowCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_VIEW_WINDOW, true, false, id);
    }
}
