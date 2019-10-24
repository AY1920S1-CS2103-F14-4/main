package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.DriverManager;
import seedu.address.model.person.Customer;
import seedu.address.model.CustomerManager;
import seedu.address.model.person.Driver;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * Contains all managers that are serializable to JSON format. Managers such as
 * {@code CustomerManager}, {@code DriverManager} and {@code TaskManager}.
 *
 */
@JsonRootName(value = "deliveria")
public class JsonSerializableCentralManager {

    public static final String MESSAGE_DUPLICATE_ENTITY = "%1$s's list contains duplicate(s).";

    //temp
    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedDriver> drivers = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCentralisedManager} with the given lists of entity.
     * Entity such as customers, drivers and tasks.
     * It loads JSON file into List for {@code Model} usage.
     */
    @JsonCreator
    public JsonSerializableCentralManager(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.customers.addAll(customers);
        this.drivers.addAll(drivers);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts all the managers in {@code centralManager} for JSON use.
     * It saves the managers into JSON format.
     *
     * @param centralManager future changes to this will not affect created {@code JsonSerializableCentralisedManager}.
     */
    public JsonSerializableCentralManager(CentralManager centralManager) {
        CustomerManager customerManager = centralManager.getCustomerManager();
        customers.addAll(customerManager.getPersonList().stream().map(JsonAdaptedCustomer::new)
                .collect(Collectors.toList()));

        DriverManager driverManager = centralManager.getDriverManager();
        drivers.addAll(driverManager.getPersonList().stream().map(JsonAdaptedDriver::new)
                .collect(Collectors.toList()));

        TaskManager taskManager = centralManager.getTaskManager();
        tasks.addAll(taskManager.getList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts all the managers in {@code CentralManager} into the model's manager objects.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CentralManager toModelType() throws IllegalValueException {
        CustomerManager customerManager = getPopulatedCustomerManager();
        DriverManager driverManager = getPopulatedDriverManager();

        //task depends on customer and driver, so need those managers as inputs.
        TaskManager taskManager = getPopulatedTaskManager(customerManager, driverManager);

        return new CentralManager(customerManager, driverManager, taskManager);
    }

    private TaskManager getPopulatedTaskManager(CustomerManager customerManager,
                                                DriverManager driverManager) throws IllegalValueException {
        TaskManager taskManager = new TaskManager();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType(customerManager, driverManager);
            System.out.println(task.getId());
            if (taskManager.hasTask(task)) {
                throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_ENTITY, Task.class.getSimpleName()));
            }
            taskManager.addTask(task);
        }

        return taskManager;
    }

    private CustomerManager getPopulatedCustomerManager() throws IllegalValueException {
        CustomerManager customerManager = new CustomerManager();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (customerManager.hasPerson(customer)) {
                throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_ENTITY,
                                                                Customer.class.getSimpleName()));
            }
            customerManager.addPerson(customer);
        }
        return customerManager;
    }

    private DriverManager getPopulatedDriverManager() throws IllegalValueException {
        DriverManager driverManager = new DriverManager();
        for (JsonAdaptedDriver jsonAdaptedDriver : drivers) {
            Driver driver = jsonAdaptedDriver.toModelType();
            if (driverManager.hasPerson(driver)) {
                throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_ENTITY, Driver.class.getSimpleName()));
            }
            driverManager.addPerson(driver);
        }
        return driverManager;
    }
}
