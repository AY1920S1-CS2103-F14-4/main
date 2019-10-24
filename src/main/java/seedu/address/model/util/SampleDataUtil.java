package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Description;
import seedu.address.model.DriverManager;
import seedu.address.model.EventTime;
import seedu.address.model.legacy.AddressBook;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.CustomerManager;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.model.task.TaskStatus;
import seedu.address.storage.CentralManager;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")),
                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")),
                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"))
        };
    }

    public static Customer[] getSampleCustomer() {
        //temp
        //need to change after customer constructor has a id parameter
        return new Customer[]{
                new Customer(1, new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("customer")),
                new Customer(2, new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("customers"))
        };
    }

    public static Driver[] getSampleDriver() {
        //temp
        //need to change after driver constructor has a id parameter
        return new Driver[]{
                new Driver(1, new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("driver")),
                new Driver(2, new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("driver")),
        };
    }

    public static Task[] getSampleTask(CustomerManager customerManager, DriverManager driverManager) {

        Task sampleTask1 = new Task(1, new Description("20 frozen boxes of Red groupers"),
                Task.getDateFromString("10/10/2019"));
        sampleTask1.setCustomer(customerManager.getCustomer(1));
        sampleTask1.setDriver(Optional.of(driverManager.getDriver(1)));
        sampleTask1.setEventTime(Optional.of(EventTime.parse("1000 - 1200")));
        sampleTask1.setStatus(TaskStatus.COMPLETED);

        Task sampleTask2 = new Task(2, new Description("25 boxes of A4 paper"),
                Task.getParsedLocalDate(LocalDate.now()));
        sampleTask2.setCustomer(customerManager.getCustomer(1));

        Task sampleTask3 = new Task(3, new Description("25 packs of frozen chicken wings"),
                Task.getParsedLocalDate(LocalDate.now()));
        sampleTask3.setCustomer(customerManager.getCustomer(1));
        sampleTask3.setDriver(Optional.of(driverManager.getDriver(1)));
        //populate the schedule of driver
        driverManager.getDriver(1).addToSchedule(EventTime.parse("1000 - 1200"));
        sampleTask3.setEventTime(Optional.of(EventTime.parse("1000 - 1200")));

        return new Task[]{sampleTask1, sampleTask2, sampleTask3};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static CentralManager getSampleCentralManager() {
        CustomerManager sampleCustomerManager = getSampleCustomerManager();
        DriverManager sampleDriverManager = getSampleDriverManager();
        TaskManager sampleTaskManager = getSampleTaskManager(sampleCustomerManager, sampleDriverManager);

        return new CentralManager(sampleCustomerManager, sampleDriverManager, sampleTaskManager);
    }

    private static CustomerManager getSampleCustomerManager() {
        CustomerManager sampleCustomerManager = new CustomerManager();
        for (Customer customer : getSampleCustomer()) {
            sampleCustomerManager.addPerson(customer);
        }
        return sampleCustomerManager;
    }

    private static DriverManager getSampleDriverManager() {
        DriverManager sampleDriverManager = new DriverManager();
        for (Driver driver : getSampleDriver()) {
            sampleDriverManager.addPerson(driver);
        }
        return sampleDriverManager;
    }

    private static TaskManager getSampleTaskManager(CustomerManager sampleCustomerManager,
                                                    DriverManager sampleDriverManager) {
        TaskManager sampleTaskManager = new TaskManager();
        for (Task task : getSampleTask(sampleCustomerManager, sampleDriverManager)) {
            sampleTaskManager.addTask(task);
        }
        return sampleTaskManager;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
