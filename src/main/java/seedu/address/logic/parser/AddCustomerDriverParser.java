package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCustomerDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCustomerDriverCommand object
 */
public class AddCustomerDriverParser implements Parser<AddCustomerDriverCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerDriverCommand
     * and returns an AddCustomerDriverCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCustomerDriverCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER, PREFIX_DRIVER,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        //check all prefix are present
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()
                || getNoOfPrefixesPresent(argMultimap, PREFIX_CUSTOMER, PREFIX_DRIVER) != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCustomerDriverCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Prefix foundPrefix = getPrefixPresent(argMultimap, PREFIX_CUSTOMER, PREFIX_DRIVER);
        Person person;

        if (foundPrefix.getPrefix().equals(PREFIX_CUSTOMER.getPrefix())) {
            person = new Customer(name, phone, email, address, tagList);
        } else {
            person = new Driver(name, phone, email, address, tagList);
        }

        return new AddCustomerDriverCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns number of prefixes in the given
     * {@code ArgumentMultimap}.
     */
    private static int getNoOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return (int) Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count();
    }

    /**
     * Returns the first prefixe in the given
     * {@code ArgumentMultimap}.
     */
    private static Prefix getPrefixPresent(ArgumentMultimap argumentMultimap,
                                           Prefix... prefixes) throws ParseException {
        Optional<Prefix> prefixFound = Stream
                                            .of(prefixes)
                                            .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                                            .findFirst();
        if (prefixFound.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCustomerDriverCommand.MESSAGE_USAGE));
        }

        return prefixFound.get();
    }
}
