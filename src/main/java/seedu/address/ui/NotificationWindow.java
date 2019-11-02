package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a help page
 */
public class NotificationWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(NotificationWindow.class);
    private static final String FXML = "NotificationWindow.fxml";

    private Logic logic;

    private IncompleteTaskListPanel incompleteTaskListPanel;

    @FXML
    private StackPane incompleteTaskListPanelPlaceholder;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public NotificationWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;

        incompleteTaskListPanel = new IncompleteTaskListPanel(this.logic.getIncompleteTaskList());
        incompleteTaskListPanelPlaceholder.getChildren().add(incompleteTaskListPanel.getRoot());
    }

    /**
     * Creates a new HelpWindow.
     */
    public NotificationWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the notification window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing page about the incomplete task from the previous day.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
