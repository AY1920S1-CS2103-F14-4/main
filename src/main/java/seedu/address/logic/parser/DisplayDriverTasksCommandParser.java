package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.DisplayDriverTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Driver;
import seedu.address.model.person.DriverIsTheSamePredicate;

/**
 * Parses input arguments and creates a new DisplayDriverTasksCommand object
 */
public class DisplayDriverTasksCommandParser implements Parser<DisplayDriverTasksCommand> {

    private static ObservableList<Driver> driverList;

    public void setDriverList(ObservableList<Driver> driverList) {
        this.driverList = driverList;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DisplayDriverTasksCommand
     * and returns a DisplayDriverTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayDriverTasksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayDriverTasksCommand.MESSAGE_USAGE));
        }

        int driverIndex = Integer.parseInt(trimmedArgs);
        Driver driver = driverList.get(driverIndex - 1);
        System.out.println(driver);
        return new DisplayDriverTasksCommand(new DriverIsTheSamePredicate(driver));
    }

}

