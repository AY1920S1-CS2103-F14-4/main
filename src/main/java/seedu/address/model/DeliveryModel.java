package seedu.address.model;

import seedu.address.model.task.Task;

public interface DeliveryModel extends Model {

    void addTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    void setTask(Task task);

    boolean hasCustomer(int customerId);
}
