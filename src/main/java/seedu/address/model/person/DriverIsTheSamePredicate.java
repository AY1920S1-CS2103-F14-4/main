package seedu.address.model.person;

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
        //case where task is not assigned to any driver
        if (task.getDriver().isEmpty()) {
            return false;
        }
        return StringUtil.isTheSameDriver(driverToCompare, task.getDriver().get());
    }
}
