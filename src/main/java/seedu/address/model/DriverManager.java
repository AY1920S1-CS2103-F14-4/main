package seedu.address.model;

import seedu.address.model.person.Driver;

/**
 * Manages the customer list.
 * It contains the minimal set of list operations.
 */
public class DriverManager extends EntityManager<Driver> {

    public DriverManager() {
        super();
    }

    /**
     * Retrieve driver using its unique driver id.
     *
     * @param driverId driver unique id.
     * @return Driver with the specified unique id.
     */
    public Driver getDriver(int driverId) {
        Person foundDriver = super.asUnmodifiableObservableList()
                .stream()
                .filter(person -> {
                    Driver driver = (Customer) person;
                    return driver.getId() == driverId;
                })
                .findFirst()
                .get();
        return (Driver) foundDriver;
    }

    /**
     * Checks if the driver list has a driver with {@code int customerId}.
     *
     * @param driverId customer unique id.
     */
    public boolean hasDriver(int driverId) {
        return super.asUnmodifiableObservableList()
                .stream()
                .anyMatch(person -> {
                    Driver driver = (Driver) person;
                    return driver.getId() == driverId;
                });
    }
}
