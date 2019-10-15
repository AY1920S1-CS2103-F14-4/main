package seedu.address.model;

import seedu.address.model.person.CustomerManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class DeliveryModelManager extends ModelManager implements DeliveryModel {

    private final TaskManager taskManager;
    private final CustomerManager customerManager;

    {
        this.taskManager = new TaskManager();
        this.customerManager = new CustomerManager();
    }

    public DeliveryModelManager() {
    }

    public DeliveryModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
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
