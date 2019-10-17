package seedu.address.model;

import seedu.address.model.person.Customer;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

public interface DeliveryModel extends Model {

    void addTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    boolean hasTask(int taskId);

    Task getTask(int taskId);

    void setTask(Task task);

    TaskManager getTaskManager();

    boolean hasCustomer(int customerId);

    Customer getCustomer(int customerId);
}
