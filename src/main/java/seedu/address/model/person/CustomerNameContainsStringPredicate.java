package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Customer}'s {@code Name} matches the String given.
 */
public class CustomerNameContainsStringPredicate implements Predicate<Customer> {
    private final String keyString;

    public CustomerNameContainsStringPredicate(String keyString) {
        this.keyString = keyString;
    }

    @Override
    public boolean test(Customer customer) {
        return StringUtil.containsPhraseIgnoreCase(customer.getName().fullName, keyString);
    }
}
