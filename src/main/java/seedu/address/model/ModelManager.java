package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.legacy.AddressBook;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> unassignedTasks;

    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Driver> filteredDrivers;

    private final TaskManager taskManager;
    private final CustomerManager customerManager;
    private final DriverManager driverManager;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.taskManager = new TaskManager();
        this.customerManager = new CustomerManager();
        this.driverManager = new DriverManager();
        this.addressBook = new AddressBook(addressBook);

        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskManager.getList());
        unassignedTasks = new FilteredList<>(this.taskManager.getList());

        filteredCustomers = new FilteredList<>(this.customerManager.getCustomerList());
        filteredDrivers = new FilteredList<>(this.driverManager.getDriverList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }


    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    // =========== Task Manager ===============================================================================

    public void addTask(Task task) {
        taskManager.addTask(task);
    }

    public void deleteTask(Task task) {
        taskManager.deleteTask(task);
    }

    public boolean hasTask(Task task) {
        return taskManager.hasTask(task);
    }

    public boolean hasTask(int taskId) {
        return taskManager.hasTask(taskId);
    }

    public void setTask(Task taskToEdit, Task editedTask) {
        taskManager.setTask(taskToEdit, editedTask);
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public Task getTask(int taskId) {
        return taskManager.getTask(taskId);
    }

    // =========== Customer Manager ===========================================================================
    public boolean hasCustomer(Customer customer) {
        return customerManager.hasPerson(customer);
    }

    public boolean hasCustomer(int customerId) {
        return customerManager.hasCustomer(customerId);
    }

    public void setCustomer(Customer customerToEdit, Customer editedCustomer) {
        customerManager.setCustomer(customerToEdit, editedCustomer);
    }

    public Customer getCustomer(int customerId) {
        return customerManager.getCustomer(customerId);
    }

    public void addCustomer(Customer customer) {
        customerManager.addPerson(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerManager.removePerson(customer);
    }

    // =========== Driver Manager ===========================================================================
    public boolean hasDriver(Driver driver) {
        return driverManager.hasDriver(driver);
    }

    public boolean hasDriver(int driverId) {
        return driverManager.hasDriver(driverId);
    };

    public void setDriver(Driver driverToEdit, Driver editedDriver) {
        driverManager.setDriver(driverToEdit, editedDriver);
    }
    @Override
    public void viewDriverTask(Person driverToView) {

    }

    public Driver getDriver(int driverId) {
        return driverManager.getDriver(driverId);
    }

    public void addDriver(Driver driver) {
        driverManager.addDriver(driver);
    }

    public void deleteDriver(Driver driver) {
        driverManager.deleteDriver(driver);
    }

    // =========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    // =========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate, FilteredList<Task> list) {
        requireNonNull(predicate);
        list.setPredicate(predicate);
    }

    /**
     * Returns an observable view of the list of that is filtered to unassigned tasks
     */
    @Override
    public ObservableList<Task> getUnassignedTaskList() {
        updateFilteredTaskList(PREDICATE_SHOW_UNASSIGNED, unassignedTasks);
        return unassignedTasks;
    }

    /**
     * Returns an observable view of the list of that is filtered to assigned tasks
     */
    @Override
    public ObservableList<Task> getAssignedTaskList() {
        updateFilteredTaskList(PREDICATE_SHOW_ASSIGNED, filteredTasks);
        return filteredTasks;
    }

    // =========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    // =========== Filtered Customer List Accessors =============================================================

    @Override
    public ObservableList<Driver> getFilteredDriverList() {
        return filteredDrivers;
    }

    @Override
    public void updateFilteredDriverList(Predicate<Driver> predicate) {
        requireNonNull(predicate);
        filteredDrivers.setPredicate(predicate);
    }
}
