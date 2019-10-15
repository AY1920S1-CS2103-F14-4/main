package seedu.address.model.person;

import seedu.address.model.AddressBook;

public class CustomerManager extends AddressBook {

    public CustomerManager() {
        persons = new CustomerList();
    }

    public boolean hasPerson(int customerId) {
        CustomerList customers = (CustomerList) persons;
        return customers.hasCustomer(customerId);
    }
}
