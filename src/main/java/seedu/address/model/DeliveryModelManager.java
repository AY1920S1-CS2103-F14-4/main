package seedu.address.model;

import seedu.address.model.person.CustomerManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

public class DeliveryModelManager extends ModelManager {

    private final TaskManager taskManager;
    private final CustomerManager customerManager;

    public DeliveryModelManager() {
        this.taskManager = new TaskManager();
        this.customerManager = new CustomerManager();
    }

    //=========== Task Manager ===============================================================================
    public void addTask(Task task) {
        taskManager.addTask(task);
    }

    public void deleteTask(Task task) {
        taskManager.deleteTask(task);
    }

    public boolean hasTask(Task task) {
        return taskManager.hasTask(task);
    }

    public void setTask(Task task) {
        taskManager.setTask(task);
    }

    //=========== Customer Manager ===========================================================================
    public boolean hasCustomer(int customerId) {
        return customerManager.hasPerson(customerId);
    }
}
