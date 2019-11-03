package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.task.Task;

/**
 * Tests that a {@code Driver} is equal to another (@code Driver) assigned to a (@code Task).
 */
public class DriverIsTheSamePredicate implements Predicate<Task> {
    private final Driver driverToCompare;

    public DriverIsTheSamePredicate(Driver driverToCompare) {
        this.driverToCompare = driverToCompare;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task.getDriver().get());
        return StringUtil.isTheSameDriver(driverToCompare, task.getDriver().get());
    }
}
