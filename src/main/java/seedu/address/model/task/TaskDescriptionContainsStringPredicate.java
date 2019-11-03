package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Customer}'s {@code Name} matches the String given.
 */
public class TaskDescriptionContainsStringPredicate implements Predicate<Task> {
    private final String keyString;

    public TaskDescriptionContainsStringPredicate(String keyString) {
        this.keyString = keyString;
    }

    @Override
    public boolean test(Task task) {
        return StringUtil.containsPhraseIgnoreCase(task.getDescription().toString(), keyString);
    }
}

