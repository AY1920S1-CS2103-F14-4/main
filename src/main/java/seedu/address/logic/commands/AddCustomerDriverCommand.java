package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Person;



/**
 * Adds a person to the address book.
 */
public class AddCustomerDriverCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the address book. "
            + "Parameters: "
            + PREFIX_CUSTOMER + "CUSTOMER NAME | "
            + PREFIX_DRIVER + " DRIVER NAME | "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New %1$s added: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This %1$s already exists.";

    private final Person toAdd;
    private final String className;
    private final int toAddId;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCustomerDriverCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
        this.className = person.getClass().getSimpleName();
        this.toAddId = person.getId();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCustomer(toAddId) || model.hasDriver(toAddId)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, className));
        }

        if (className.equals(Customer.class.getSimpleName())) {
            model.addCustomer( (Customer) toAdd);
        } else if (className.equals(Driver.class.getSimpleName())) {
            model.addDriver( (Driver) toAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, className, toAdd));
    }
}
