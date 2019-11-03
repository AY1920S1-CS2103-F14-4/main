package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Driver}'s {@code Name} matches the String given.
 */
public class DriverNameContainsStringPredicate implements Predicate<Driver> {
    private final String keyString;

    public DriverNameContainsStringPredicate(String keyString) {
        this.keyString = keyString;
    }

    @Override
    public boolean test(Driver driver) {
        return StringUtil.containsPhraseIgnoreCase(driver.getName().fullName, keyString);
    }
}
