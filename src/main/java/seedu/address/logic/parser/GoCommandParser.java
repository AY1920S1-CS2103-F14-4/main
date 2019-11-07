package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneCommand object
 */
public class GoCommandParser implements Parser<GoCommand> {

    public static final String HOME_TAB = "home";
    public static final String HISTORY_TAB = "history";

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     *
     * @return the parsed command
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoCommand parse(String args) throws ParseException {
        //parse args return
        String tabName = args.trim().toLowerCase();
        if (tabName.equalsIgnoreCase(HOME_TAB)) {
            return new GoCommand(HOME_TAB);
        } else if (tabName.equalsIgnoreCase(HISTORY_TAB)) {
            return new GoCommand(HISTORY_TAB);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoCommand.MESSAGE_USAGE));
        }
    }
}
