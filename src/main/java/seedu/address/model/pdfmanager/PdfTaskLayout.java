package seedu.address.model.pdfmanager;

import java.util.Optional;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import seedu.address.model.Description;
import seedu.address.model.EventTime;
import seedu.address.model.pdfmanager.PdfTable;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

public class PdfTaskLayout extends PdfTable {

    private Task task;

    public PdfTaskLayout(Task task) {
        this.task = task;
    }

    public Table createTable() {
        //setting basic layout
        Table taskTable = new Table(10).useAllAvailableWidth().setFixedLayout();

        //populate cells
        Cell taskIdCell = getTaskIdCell(task.getId());
        Cell descriptionCell = getDescriptionCell(task.getDescription());
        Cell statusCell = getStatusCell(task.getStatus());

        Optional<EventTime> optionalEventTime = task.getEventTime();
        assert optionalEventTime.isPresent();
        Cell eventTimeCell = getEventTimeCell(optionalEventTime.get());

        taskTable.addCell(eventTimeCell);
        taskTable.addCell(taskIdCell);
        taskTable.addCell(descriptionCell);
        taskTable.addCell(statusCell);

        //create customer table
        PdfCustomerLayout customerLayout = new PdfCustomerLayout(task.getCustomer());
        Table customerTable = customerLayout.createTable();

        //add customer table into task table
        Cell customerTableCell = new Cell(1,8).add(customerTable);
        taskTable.addCell(customerTableCell);

        return taskTable;
    }

    public Cell getTaskIdCell(int taskId) {
        String idStr = "Task ID: \n" + taskId;
        return createAndPopulate(1,1, idStr);
    }

    public Cell getDescriptionCell(Description description) {
        String descriptionStr = "Goods: \n" + description;
        return createAndPopulate(1,5, descriptionStr);
    }

    public Cell getStatusCell(TaskStatus status) {
        String statusStr = "Status: \n" + status;
        return createAndPopulate(1,2, statusStr);
    }

    public Cell getEventTimeCell(EventTime eventTime) {
        String eventTimeStr = eventTime.toString();
        return createAndPopulate(2,2, eventTimeStr);
    }
}
