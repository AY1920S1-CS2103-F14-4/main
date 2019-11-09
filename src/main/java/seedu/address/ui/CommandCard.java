package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CommandCard extends UiPart {
    private static final String FXML = "CommandListCard.fxml";

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label command;


    public CommandCard(String command, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        this.command.setText(command);
    }

}
