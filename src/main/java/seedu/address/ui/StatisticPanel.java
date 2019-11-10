package seedu.address.ui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class StatisticPanel {

    @FXML
    private BarChart<?, ?> customerChart;

    @FXML
    private CategoryAxis customerAxis;

    @FXML
    private NumberAxis orderAxis;

    @FXML
    private BarChart<?, ?> driverChart;

    @FXML
    private CategoryAxis driverAxis;

    @FXML
    private NumberAxis taskAxis;

    private void getChartData() {

        Observable data = FXCollections.observableArrayList();

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("customerID#1", 3));
        set1.getData().add(new XYChart.Data("customerID#2", 6));

        customerChart.getData().addAll(set1);
    }

}
