package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AssignCommand.forceAssign;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import seedu.address.commons.core.Messages;
import seedu.address.logic.GlobalClock;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventTime;
import seedu.address.model.Model;
import seedu.address.model.person.Driver;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Edits the details of an existing person in the address book.
 */
public class SuggestCommand extends Command {
    public static final String COMMAND_WORD = "suggest";
    public static final String MESSAGE_NO_DRIVER_AVAILABLE = "No driver is available for this duration. ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Suggest and assign an available driver, given " +
            "whose schedule can fit the proposed duration. "
            + "\n"
            + "Parameters: [NUMBER_OF_HOURS] "
            + "[" + PREFIX_TASK + "TASK_ID] " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1.5" + " "
            + PREFIX_TASK + "3 ";

    private Duration duration;
    private int taskId;

    /**
     * @param taskId   task's ID
     * @param duration duration
     */
    public SuggestCommand(int taskId, Duration duration) {
        requireNonNull(duration);

        this.taskId = taskId;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // fails when task cannot be found
        if (!model.hasTask(taskId)) {
            throw new CommandException(Task.MESSAGE_INVALID_ID);
        }


        Task task = model.getTask(taskId);
        // check whether the task is scheduled for today
        if (!task.getDate().equals(GlobalClock.dateToday())) {
            throw new CommandException(Messages.MESSAGE_NOT_TODAY);
        }


        // check whether the task is already scheduled
        if (task.getStatus() != TaskStatus.INCOMPLETE || task.getDriver().isPresent()
                || task.getEventTime().isPresent()) {
            throw new CommandException(Messages.MESSAGE_ALREADY_ASSIGNED);
        }

        List<Driver> driverList = model.getDriverManager().getDriverList();

        Candidate result = driverList.stream()
                .map(driver -> new Candidate(driver, driver.suggestTime(duration, GlobalClock.timeNow())))
                // filter out the candidate who has no available time
                .filter(candidate -> candidate.getValue().isPresent())
                // find the earliest driver-time pair
                .min(Candidate.comparator())
                .orElseThrow(() -> new CommandException(MESSAGE_NO_DRIVER_AVAILABLE));


        forceAssign(result.getKey(), task, result.getValue().get());

        model.refreshAllFilteredList();

        return new CommandResult(String.format(Messages.MESSAGE_ASSIGN_SUCCESS,
                task.getId(), result.getKey().getName().fullName, result.getValue().get().toString()));
    }

}

class Candidate extends Pair<Driver, Optional<EventTime>> {

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Candidate(Driver key, Optional<EventTime> value) {
        super(key, value);
    }

    public static Comparator<Candidate> comparator() {
        return (o1, o2) -> {
            // unpack
            Optional<EventTime> t1 = o1.getValue();
            Optional<EventTime> t2 = o2.getValue();

            if (t1.isPresent() && t2.isPresent()) {
                return t1.get().compareTo(t2.get());
            } else if (t1.isEmpty()) {
                return 1;
            } else {
                return -1;
            }
        };
    }
}
