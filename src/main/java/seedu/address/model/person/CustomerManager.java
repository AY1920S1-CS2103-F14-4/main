package seedu.address.model.person;

import seedu.address.model.AddressBook;

public class CustomerManager extends AddressBook {

    private final CustomerList customers;

    public CustomerManager() {
        customers = new CustomerList();
    }

    public boolean hasPerson(int customerId) {
        return customers.hasCustomer(customerId);
    }
}
