package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.GlobalClock;
import seedu.address.model.id.IdManager;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.pdfmanager.exceptions.PdfNoTaskToDisplayException;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.model.task.TaskStatus;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_DRIVERS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false
     */
    Predicate<Task> PREDICATE_SHOW_EMPTY_TASKS = unused -> false;
    Predicate<Person> PREDICATE_SHOW_EMPTY_CUSTOMERS = unused -> false;
    Predicate<Person> PREDICATE_SHOW_EMPTY_DRIVERS = unused -> false;

    /**
     * {@code Predicate} that filters the task to incomplete status
     */
    Predicate<Task> PREDICATE_SHOW_UNASSIGNED = task -> task.getStatus().equals(TaskStatus.INCOMPLETE);

    /**
     * {@code Predicate} that filters the task to on-going status
     */
    Predicate<Task> PREDICATE_SHOW_ASSIGNED = task -> task.getStatus().equals(TaskStatus.ON_GOING);

    /**
     * {@code Predicate} that filters the task to completed status
     */
    Predicate<Task> PREDICATE_SHOW_COMPLETED = task -> task.getStatus().equals(TaskStatus.COMPLETED);

    /**
     * {@code Predicate} that filters the task to both incomplete and ongoing status
     */
    Predicate<Task> PREDICATE_SHOW_PREVIOUS_DAYS = task -> task.getDate().isBefore(GlobalClock.dateToday());

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person. The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person. {@code person} must not already exist in the address
     * book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book. The person identity of
     * {@code editedPerson} must not be the same as another existing person in the
     * address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // central manager

    void resetCentralManager();

    // task manager

    void addTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    boolean hasTask(int taskId);

    Task getTask(int taskId);

    void setTask(Task taskToEdit, Task editedTask);

    TaskManager getTaskManager();

    boolean hasTaskBelongsToDriver(Driver driver);

    boolean hasTaskBelongsToCustomer(Customer customer);

    /**
     * A control-check update to ensure that the AssignedTaskList will be updated to display the default assigned tasks.
     */
    void updateAssignedTaskList();

    /**
     * Returns an unmodifiable view of the filtered unassigned task list.
     */
    ObservableList<Task> getUnassignedTaskList();

    /**
     * Returns an unmodifiable view of the filtered assigned task list.
     */
    ObservableList<Task> getAssignedTaskList();

    /**
     * Return a list of incomplete tasks from the previous days
     */
    ObservableList<Task> getIncompleteTaskList();

    /**
     * Returns an unmodifiable view of the completed assigned task list.
     */
    ObservableList<Task> getCompletedTaskList();

    /**
     * Returns an unmodifiable view of the current completed assigned task list.
     */
    ObservableList<Task> getCurrentCompletedTaskList();

    // customer manager

    CustomerManager getCustomerManager();

    boolean hasCustomer(Customer customer);

    boolean hasCustomer(int customerId);

    Customer getCustomer(int customerId);

    void viewCustomerTask(int customerId);

    void viewDriverTask(int driverId);

    void setCustomer(Customer customerToEdit, Customer editedTask);

    void addCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    // driver manager

    DriverManager getDriverManager();

    boolean hasDriver(Driver driver);

    boolean hasDriver(int driverId);

    Driver getDriver(int driverId);

    void setDriver(Driver driverToEdit, Driver editedTask);

    void addDriver(Driver driver);

    void deleteDriver(Driver driver);

    /**
     * Returns an unmodifiable view of the filtered task list.
     */

    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate, FilteredList<Task> list);

    void updateFilteredTaskList(Predicate<Task> predicate);

    void refreshFilteredTaskList();

    void refreshAllFilteredList();

    /**
     * Updates the filter of the completed filtered task list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateCompletedTaskList(Predicate<Task> predicate);

    /**
     * Returns an unmodifiable view of the filtered customer list.
     */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Updates the filter of the filtered customer list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Person> predicate);

    void updateFilteredDriverList(Predicate<Person> predicate);

    void refreshFilteredCustomerList();

    /**
     * Returns an unmodifiable view of the filtered driver list.
     */
    ObservableList<Driver> getFilteredDriverList();

    void refreshFilteredDriverList();

    int getNextTaskId();

    int getNextCustomerId();

    int getNextDriverId();

    IdManager getIdManager();

    boolean isStartAfresh();

    void saveDriverTaskPdf(String filePathForPdf, LocalDate date) throws IOException, PdfNoTaskToDisplayException;

    void commitTaskManager();

    void commitCustomerManager();

    void commitDriverManager();

    void commitManagers();

    boolean canUndoManagers();

    void undoTaskManager();

    void undoCustomerManager();

    void undoDriverManager();

    void undoManagers();

    boolean canRedoTaskManager();

    boolean canRedoCustomerManager();

    boolean canRedoDriverManager();

    boolean canRedoManagers();

    void redoTaskManager();

    void redoCustomerManager();

    void redoDriverManager();

    void redoManagers();

    boolean shouldTruncateManagers();

    void truncateManagers();
}
