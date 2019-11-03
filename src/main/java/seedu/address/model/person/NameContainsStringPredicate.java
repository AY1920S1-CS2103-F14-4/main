package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches the String given.
 */
public class NameContainsStringPredicate implements Predicate<Person> {
    private final String keyString;

    public NameContainsStringPredicate(String keyString) {
        this.keyString = keyString;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsPhraseIgnoreCase(person.getName().fullName, keyString);
    }
}
