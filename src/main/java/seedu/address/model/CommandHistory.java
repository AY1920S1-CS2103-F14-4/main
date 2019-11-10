package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.util.Objects.requireNonNull;

/**
 * Keep track of the past commands executed by the User.
 */
public class CommandHistory {

    private final ObservableList<String> commandList = FXCollections.observableArrayList();
    private final ObservableList<String> commandListUnmodifiable = FXCollections.unmodifiableObservableList(commandList);

    String command;

    public CommandHistory() {
        commandList.add("test");
        commandList.add("test2");
    }

    public CommandHistory(String command) {
        this.command = command;
    }

    /**
     * Adds a command into the command list.
     *
     * @param command inputted command by user.
     */
    public void addCommand(String command) {
        requireNonNull(command);
        commandList.add(command);
    }


    public ObservableList<String> getCommandList() {
        return commandList;
    }

}
