package seedu.address.ui;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Customer;

/**
 * Controller for a help page
 */
public class CustomerWindow extends UiPart<Stage> {

    public static final String DEFAULT_MESSAGE = "Customer Window";

    private static final String CODED = "QUl6YVN5QmtSOTlwR0EwSElTUFQxSFA2d1NLOFd2cTBPZGdCbU9J";
    private static final String WEBVIEW_WRAPPER = "<html style=\"background: #424242;\">%s</html>";
    private static final String MAPS_WRAPPER = "<iframe width=\"100%%\" height=\"330\" frameborder=\"0\" "
            + "style=\"border:0\" src=\"https://www.google.com/maps/embed/v1/search?q=%s&key=%s\" "
            + "allowfullscreen></iframe>";
    private static final Logger logger = LogsCenter.getLogger(CustomerWindow.class);
    private static final String FXML = "CustomerWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label customerId;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private WebView map;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CustomerWindow(Stage root) {
        super(FXML, root);
        name.setText(DEFAULT_MESSAGE);
        phone.setText(DEFAULT_MESSAGE);
        customerId.setText(DEFAULT_MESSAGE);
        email.setText(DEFAULT_MESSAGE);
        address.setText(DEFAULT_MESSAGE);
        map.getEngine().loadContent(String.format(WEBVIEW_WRAPPER, ""));
    }

    /**
     * Creates a new HelpWindow.
     */
    public CustomerWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing customer window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the customer window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the customer window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the customer window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Populate fields with relevant details.
     */
    public void fillFields(Customer customer) {
        name.setText(customer.getName().fullName);
        phone.setText("Phone: " + customer.getPhone().value);
        customerId.setText("Customer ID: #" + customer.getId());
        email.setText("Email: " + customer.getEmail());
        address.setText("Address: " + customer.getAddress());
        // Map
        String addressString = String.format(MAPS_WRAPPER,
                URLEncoder.encode(customer.getAddress().value, StandardCharsets.UTF_8),
                new String(Base64.getDecoder().decode(CODED)));
        map.getEngine().loadContent(String.format(WEBVIEW_WRAPPER, addressString));
    }
}
