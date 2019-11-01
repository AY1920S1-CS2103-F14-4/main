package seedu.address.model.pdfmanager;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

public class PdfSampleLayout extends PdfLayout {

    public PdfSampleLayout() {

    }

    public Table createTable() {
        Table sampleTable = new Table(10).useAllAvailableWidth().setFixedLayout();

        Cell titleCell = createCell(1, 10, "SAMPLE DRIVER'S TASK.");
        titleCell.setBold();

        Cell eventTimeCell = createCell(2, 2, "Duration of Delivery");
        Cell taskDetailCell = createCell(1, 8, "Task's Information\n");
        Cell customerDetailCell = createCell(1, 8, "Customer's Information\n");

        sampleTable.addCell(titleCell);
        sampleTable.addCell(insertPadding(eventTimeCell));
        sampleTable.addCell(insertPadding(taskDetailCell));
        sampleTable.addCell(insertPadding(customerDetailCell));

        return sampleTable;
    }
}
