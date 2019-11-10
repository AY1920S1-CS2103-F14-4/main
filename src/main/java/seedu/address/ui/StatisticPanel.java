package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.task.Task;

import java.util.HashMap;

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

    public StatisticPanel(Logic logicStatistic) {
        super(FXML);
        getChartData(logicStatistic);
    }

    private void getChartData(Logic logicStatistic) {

        XYChart.Series<String, Double> customerSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> driverSeries = new XYChart.Series<>();

        ObservableList<Task> tasks = logicStatistic.getFilteredTaskList();
        ObservableList<Task> completedTasks = logicStatistic.getFilteredCompletedTaskList();

        HashMap<Integer, Integer> customerData = new HashMap<>();
        HashMap<Integer, Integer> driverData = new HashMap<>();

        //Loops through the tasks and add the number of orders to each customer ID storing as HashMap
        //Customers with zero orders will not be displayed in the bar chart
        for (Task task : tasks) {
            if (customerData.containsKey(task.getCustomer().getId())) {
                customerData.replace(task.getCustomer().getId(), customerData.get(task.getCustomer().getId()) + 1);
            } else  {
                customerData.put(task.getCustomer().getId(), 1);
            }
        }

        for (Task task : completedTasks) {
            if (driverData.containsKey(task.getDriver().get().getId())) {
                driverData.replace(task.getDriver().get().getId(), driverData.get(task.getDriver().get().getId()) + 1);
            } else  {
                driverData.put(task.getDriver().get().getId(), 1);
            }
        }

        customerData.forEach((id, order) -> customerSeries.getData().add(new XYChart.Data("#" + id, order)));

        driverData.forEach((id, delivery) -> driverSeries.getData().add(new XYChart.Data("#" + id, delivery)));

        customerChart.getData().add(customerSeries);
        customerChart.setLegendVisible(false);

        driverChart.getData().add(driverSeries);
        driverChart.setLegendVisible(false);

    }

}
