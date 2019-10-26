package seedu.address.storage;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CentralManager that = (CentralManager) o;
        return getCustomerManager().equals(that.getCustomerManager()) &&
                getDriverManager().equals(that.getDriverManager()) &&
                getTaskManager().equals(that.getTaskManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerManager(), getDriverManager(), getTaskManager());
    }
}
