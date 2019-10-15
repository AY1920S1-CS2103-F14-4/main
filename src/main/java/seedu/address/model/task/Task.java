package seedu.address.model.task;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.Goods;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.task.execeptions.TaskException;

/**
 * Represents a delivery task. All the tasks are represented by a unique id
 * for differentiation.
 */
public class Task {

    private int id;
    private Goods goods;
    private String dateTime;
    private Optional<Driver> driver;
    private Customer customer;

    private TaskStatus status;

    public Task(int id, Goods goods) {
        this.id = id;
        this.goods = goods;
        status = TaskStatus.INCOMPLETE;
    }

    //get methods
    public int getId() {
        return id;
    }

    public Goods getGoods() {
        return goods;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Driver getDriver() {
        if (!driver.isPresent()) {
            throw new TaskException("There is no driver assigned to the task.");
        }
        return driver.get();
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isAssign() {
        return getDriver() != null;
    }

    //set methods
    public void setStatus(TaskStatus status) {
        if (this.status == status) {
            throw new TaskException("Task's status is already set to " + status);
        }
        this.status = status;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDriver(Driver driver) {
        this.driver = Optional.of(driver);

        setStatus(TaskStatus.ON_GOING);
    }

    /**
     * Deletes the driver assigned from the task.
     */
    public void deleteDriver() {
        driver = Optional.empty();

        setStatus(TaskStatus.INCOMPLETE);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void markAsDone() {
        setStatus(TaskStatus.COMPLETED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        // If task id is the same, then the task is the same regardless of other variables.
        return getId() == task.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    @Override
    public String toString() {
        return getId() + ". " + getGoods() + " " + getStatus();
    }
}
