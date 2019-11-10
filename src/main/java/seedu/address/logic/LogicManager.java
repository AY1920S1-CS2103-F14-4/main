package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.storage.CentralManager;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final CommandHistory commandHistory;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        commandHistory = new CommandHistory();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        //Add the successful commands to the history command list and update
        commandHistory.addCommand(commandText);
        model.updateCommandList(Model.PREDICATE_SHOW_ALL_COMMANDS, model.getFilteredCommandList());

        System.out.println(commandText);
        System.out.println(commandHistory.getCommandList().size());
        try {
            storage.saveManager(new CentralManager(model.getCustomerManager(), model.getDriverManager(),
                    model.getTaskManager(), model.getIdManager()));
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredUnassignedTaskList() {
        return model.getUnassignedTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredAssignedTaskList() {
        return model.getAssignedTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredCompletedTaskList() {
        return model.getCompletedTaskList();
    }

    @Override
    public ObservableList<Task> getIncompleteTaskList() {
        return model.getIncompleteTaskList();
    }

    @Override
    public ObservableList<Driver> getFilteredDriverList() {
        return model.getFilteredDriverList();
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return model.getFilteredCustomerList();
    }

    @Override
    public ObservableList<String> getCommandList() {
        return model.getCommandList();
    }

    @Override
    public boolean isStartAfresh() {
        return model.isStartAfresh();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
