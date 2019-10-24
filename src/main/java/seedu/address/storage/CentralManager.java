package seedu.address.storage;

import seedu.address.model.DriverManager;
import seedu.address.model.CustomerManager;
import seedu.address.model.task.TaskManager;

public class CentralManager {

    private CustomerManager customerManager;
    private DriverManager driverManager;
    private TaskManager taskManager;

    public CentralManager() {
        this.customerManager = new CustomerManager();
        this.driverManager = new DriverManager();
        this.taskManager = new TaskManager();
    }

    public CentralManager(CustomerManager customerManager, DriverManager driverManager, TaskManager taskManager) {
        this.customerManager = customerManager;
        this.driverManager = driverManager;
        this.taskManager = taskManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
