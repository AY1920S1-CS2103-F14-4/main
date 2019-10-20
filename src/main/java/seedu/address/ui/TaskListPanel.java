package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;

import java.util.logging.Logger;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;

    public TaskListPanel(ObservableList<Task> personList) {
        super(FXML);
        taskListView.setItems(personList);
        taskListView.setCellFactory(listView -> new TaskListPanel.TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
