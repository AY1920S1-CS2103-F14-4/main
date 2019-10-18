package seedu.address.model;

import seedu.address.model.person.Driver;
import seedu.address.model.person.exceptions.PersonNotFoundException;

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
        return getPersonList()
                .stream()
                .filter(driver -> driver.getId() == driverId)
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Checks if the driver list has a driver with {@code int customerId}.
     *
     * @param driverId customer unique id.
     */
    public boolean hasDriver(int driverId) {
        return getPersonList()
                .stream()
                .anyMatch(driver -> driver.getId() == driverId);
    }
}
