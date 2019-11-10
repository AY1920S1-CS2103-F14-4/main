package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;

/**
 * Displays the statistic tab with bar charts.
 */
public class StatisticPanel extends UiPart<Region> {

    public static final String FXML = "StatisticPanel.fxml";

    @FXML
    private BarChart<String, Double> customerChart;

    @FXML
    private CategoryAxis customerAxis;

    @FXML
    private NumberAxis orderAxis;

    @FXML
    private BarChart<String, Double> driverChart;

    @FXML
    private CategoryAxis driverAxis;

    @FXML
    private NumberAxis taskAxis;

    private Logic logicStatistic;

    public StatisticPanel(Logic logicStatistic) {
        super(FXML);
        this.logicStatistic = logicStatistic;
        getChartData(logicStatistic);
    }

    private void getChartData(Logic logicStatistic) {

        XYChart.Series<String, Double> customerSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> driverSeries = new XYChart.Series<>();

//        customerSeries.getData().add(new XYChart.Data("customerID#1",
//                model.getFilteredTaskList().filtered(task -> task.getCustomer().getId() == 1).size()));
//
//        customerSeries.getData().add(new XYChart.Data("customerID#2",
//                model.getFilteredTaskList().filtered(task -> task.getCustomer().getId() == 2).size()));
//
//        driverSeries.getData().add(new XYChart.Data("driverID#1",
//                model.getFilteredTaskList().filtered(task -> task.getDriver().get().getId() == 1).size()));
//
//        driverSeries.getData().add(new XYChart.Data("driverID#1",
//                model.getFilteredTaskList().filtered(task -> task.getDriver().get().getId() == 2).size()));

        customerSeries.getData().add(new XYChart.Data("1", 3));

        customerSeries.getData().add(new XYChart.Data("2", 4));

        driverSeries.getData().add(new XYChart.Data("1",2));

        driverSeries.getData().add(new XYChart.Data("2", 3));

        customerChart.getData().add(customerSeries);
        driverChart.getData().add(driverSeries);

        driverChart.setLegendVisible(false);
        customerChart.setLegendVisible(false);
    }

}
