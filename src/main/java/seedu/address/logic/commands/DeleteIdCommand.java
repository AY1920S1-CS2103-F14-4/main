package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DeliveryModel;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class DeleteIdCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a Task / Driver / Customer using their UNIQUE ID.\n"
            + "Parameters: " + PREFIX_TASK + " Task ID | "
            + "Parameters: " + PREFIX_CUSTOMER + " Customer ID | "
            + "Parameters: " + PREFIX_DRIVER + " Driver ID \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + " 10 \n"
            + "Example 2: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER + " 2";

    public static final String MESSAGE_INVALID_TASK_ID = "Invalid task id.";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted %1$s: %2$s";

    private final String className;
    private final int id;

    public DeleteIdCommand(String className, int id) {
        this.id = id;
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        DeliveryModel deliveryModel = (DeliveryModel) model;

        if (className.equals(Task.class.getSimpleName())) {
            //deletion for Task
            if (!deliveryModel.hasTask(id)) {
                throw new CommandException(MESSAGE_INVALID_TASK_ID);
            }

            Task taskToDelete = deliveryModel.getTask(id);
            deliveryModel.deleteTask(taskToDelete);

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, className, taskToDelete));
        } else {
            //temp
            //deletion for Customer
            //deletion for Driver
            return new CommandResult("temp");
        }
    }
}
