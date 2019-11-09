package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Schedule;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DriverCard extends UiPart<Region> {

    private static final String FXML = "DriverListCard.fxml";

    public final Driver driver;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label driverId;
    @FXML
    private Label availability;
    @FXML
    private Label rating;

    public DriverCard(Driver driver, int displayedIndex) {
        super(FXML);
        this.driver = driver;
        id.setText(displayedIndex + ". ");
        name.setText(driver.getName().fullName);
        phone.setText("Phone: " + driver.getPhone().value);
        driverId.setText("Driver ID: #" + driver.getId());
        if (driver.getRating() == 0) {
            rating.setText("Driver Rating: Not rated yet");
        } else {
            rating.setText("Driver Rating: " + driver.getRating() + "/5");
        }
        if (driver.getSchedule().toString().equals(Schedule.MESSAGE_EMPTY_SCHEDULE)) {
            availability.setText("Unavailable Time: Available all times");
        } else {
            availability.setText("Unavailable Time: " + driver.getSchedule());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DriverCard)) {
            return false;
        }

        // state check
        DriverCard card = (DriverCard) other;
        return id.getText().equals(card.id.getText())
                && driver.equals(card.driver);
    }
}
