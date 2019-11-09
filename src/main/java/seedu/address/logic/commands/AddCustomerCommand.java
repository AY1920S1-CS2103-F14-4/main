package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Adds a person to the address book.
 */
public class AddCustomerCommand extends Command {

    public static final String COMMAND_WORD = "addC";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "CUSTOMER NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New Customer added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This Customer already exists.";

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tags;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCustomerCommand(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Customer customerToAdd = new Customer(model.getNextCustomerId(), name, phone, email, address, tags);

        if (model.hasCustomer(customerToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON));
        }

        if (model.shouldTruncateManagers()) {
            model.truncateManagers();
        }
        model.addCustomer(customerToAdd);
        model.commitManagers();

        return new CommandResult(String.format(MESSAGE_SUCCESS, customerToAdd));
    }
}
