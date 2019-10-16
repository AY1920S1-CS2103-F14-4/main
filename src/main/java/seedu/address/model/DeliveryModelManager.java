package seedu.address.model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.CustomerManager;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

import java.util.HashSet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class DeliveryModelManager extends ModelManager implements DeliveryModel {

    private final TaskManager taskManager;
    private final CustomerManager customerManager;

    {
        this.taskManager = new TaskManager();
        this.customerManager = new CustomerManager();

        //temp
        Customer testCustomer = new Customer(new Name("Alesx Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new HashSet<Tag>());
        customerManager.addPerson(testCustomer);
    }

    public DeliveryModelManager() {
        super();
    }

    public DeliveryModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super(addressBook, userPrefs);
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

    public TaskManager getTaskManager() {
        return taskManager;
    }

    //=========== Customer Manager ===========================================================================
    public boolean hasCustomer(int customerId) {
        return customerManager.hasPerson(customerId);
    }

    public Customer getCustomer(int customerId) {
        return customerManager.getCustomer(customerId);
    }
}
