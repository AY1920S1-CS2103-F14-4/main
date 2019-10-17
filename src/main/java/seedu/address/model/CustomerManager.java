package seedu.address.model;

import seedu.address.model.person.Customer;

/**
 * Manages the customer list.
 * It contains the minimal set of list operations.
 */
public class CustomerManager extends EntityManager<Customer> {

    public CustomerManager() {
        super();
    }

    /**
     * Retrieve customer using its unique customer id.
     *
     * @param customerId customer unique id.
     * @return Customer with the specified unique id.
     */
    public Customer getCustomer(int customerId) {
        Customer foundCustomer = (Customer) super.getPersonList()
                .stream()
                .filter(person -> {
                    Customer customer = (Customer) person;
                    return customer.getId() == customerId;
                })
                .findFirst()
                .get();
        return (Customer) foundCustomer;
    }

    /**
     * Checks if the customer list has a customer with {@code int customerId}.
     *
     * @param customerId customer unique id.
     */
    public boolean hasCustomer(int customerId) {
        return super.getPersonList()
                .stream()
                .anyMatch(person -> {
                    Customer customer = (Customer) person;
                    return customer.getId() == customerId;
                });
    }
}
