package seedu.address.logic.parser;

import seedu.address.logic.commands.FindDriverCommand;
import seedu.address.logic.commands.ViewCustomerTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewCustomerTaskCommandParser implements Parser<ViewCustomerTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCustomerTaskCommand
     * and returns a ViewCustomerCommand object for execution.
     * @throws ParseException if the  user input does not conform the expected format
     */
    public ViewCustomerTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDriverCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDriverCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
