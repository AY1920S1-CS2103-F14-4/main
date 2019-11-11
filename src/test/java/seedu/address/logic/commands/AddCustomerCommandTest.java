package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CustomerManager;
import seedu.address.model.DriverManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.id.IdManager;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.pdfmanager.exceptions.PdfNoTaskToDisplayException;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.CustomerBuilder;

public class AddCustomerCommandTest {

    @Test
    public void constructor_nullustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCustomerCommand(null, null, null,
                null, null));
    }

    @Test
    public void execute_customerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCustomerAdded modelStub = new ModelStubAcceptingCustomerAdded();
        Customer validCustomer = new CustomerBuilder().build();

        CommandResult commandResult = new AddCustomerCommand(validCustomer.getName(), validCustomer.getPhone(),
                validCustomer.getEmail(), validCustomer.getAddress(), validCustomer.getTags()).execute(modelStub);

        assertEquals(String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.customersAdded);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        Customer validCustomer = new CustomerBuilder().build();
        AddCustomerCommand addCustomerCommand = new AddCustomerCommand(validCustomer.getName(),
                validCustomer.getPhone(), validCustomer.getEmail(), validCustomer.getAddress(),
                validCustomer.getTags());
        ModelStub modelStub = new ModelStubWithCustomer(validCustomer);

        assertThrows(CommandException.class, AddCustomerCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addCustomerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Customer alice = new CustomerBuilder().withName("Alice").build();
        Customer bob = new CustomerBuilder().withName("Bob").build();
        AddCustomerCommand addAliceCommand = new AddCustomerCommand(alice.getName(), alice.getPhone(),
                alice.getEmail(), alice.getAddress(), alice.getTags());
        AddCustomerCommand addBobCommand = new AddCustomerCommand(bob.getName(), bob.getPhone(),
                bob.getEmail(), bob.getAddress(), bob.getTags());

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCustomerCommand addAliceCommandCopy = new AddCustomerCommand(alice.getName(), alice.getPhone(),
                alice.getEmail(), alice.getAddress(), alice.getTags());
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetCentralManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void deleteTask(Task task) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasTask(int taskId) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Task getTask(int taskId) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setTask(Task taskToEdit, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public TaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasTaskBelongsToDriver(Driver driver) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasTaskBelongsToCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCustomer(int customerId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getUnassignedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getAssignedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate, FilteredList<Task> list) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getIncompleteTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getCompletedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getCurrentCompletedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshAllFilteredList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCompletedTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Customer getCustomer(int customerId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewCustomerTask(int customerId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewDriverTask(int driverId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCustomer(Customer customerToEdit, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CustomerManager getCustomerManager() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDriver(Driver driver) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDriver(int driverId) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setDriver(Driver driverToEdit, Driver editedDriver) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Driver getDriver(int driverId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDriver(Driver driver) {
            throw new AssertionError("This method should not be called.");
        };

        public void deleteDriver(Driver driver) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public DriverManager getDriverManager() {
            throw new AssertionError("This method should not be called.");
        };

        public void updateFilteredDriverList(Predicate<Driver> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Driver> getFilteredDriverList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshFilteredDriverList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNextTaskId() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public int getNextCustomerId() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public int getNextDriverId() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public IdManager getIdManager() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean isStartAfresh() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveDriverTaskPdf(String filePathForPdf, LocalDate date)
                throws IOException, PdfNoTaskToDisplayException {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Customer.
     */
    private class ModelStubWithCustomer extends ModelStub {
        private final Customer customer;

        ModelStubWithCustomer(Customer customer) {
            requireNonNull(customer);
            this.customer = customer;
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return this.customer.isSameCustomer(customer);
        }
    }

    /**
     * A Model stub that always accept the customer being added.
     */
    private class ModelStubAcceptingCustomerAdded extends ModelStub {
        final ArrayList<Customer> customersAdded = new ArrayList<>();

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return customersAdded.stream().anyMatch(customer::isSameCustomer);
        }

        @Override
        public void addCustomer(Customer customer) {
            requireNonNull(customer);
            customersAdded.add(customer);
        }

        @Override
        public CustomerManager getCustomerManager() {
            return new CustomerManager();
        }
    }

}
