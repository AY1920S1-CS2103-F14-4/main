package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class CommandCard extends UiPart<Region> {

    private static final String FXML = "CommandCard.fxml";

//    public final String string;

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label command;


    public CommandCard(String string, int displayedIndex) {
        super(FXML);
//        this.string = string;
        id.setText(displayedIndex + ". ");
        command.setText(string);
    }

}
