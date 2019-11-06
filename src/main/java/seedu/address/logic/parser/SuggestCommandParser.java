package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.EventTime;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SuggestCommandParser implements Parser<SuggestCommand> {


    private static ParseException getWrongFormatException() {
        return new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @return the parsed command
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DRIVER, PREFIX_TASK);
        // TODO: check if all these are present, and preamble
//        boolean isForce = argMultimap.getPreamble().equals("force");
//        boolean isEmpty = argMultimap.getPreamble().isEmpty();

        String task = argMultimap.getValue(PREFIX_TASK).orElseThrow(SuggestCommandParser::getWrongFormatException);
        int taskId = ParserUtil.parseId(task);

        String duration = argMultimap.getPreamble();

        // 1.5


        return new SuggestCommand();
    }

}
