package seedu.address.model.pdfmanager;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

public class PdfTableHeaderLayout extends PdfLayout {

    public PdfTableHeaderLayout() {
    }

    public Table createTable() {
        Table headerTable = new Table(10).useAllAvailableWidth().setFixedLayout();

        Cell eventTimeTitle = getEventTimeTitle();
        Cell taskTitle = getTaskTitle();

        headerTable.addCell(boldCell(eventTimeTitle));
        headerTable.addCell(boldCell(taskTitle));

        return designTable(headerTable);
    }

    private Cell getEventTimeTitle() {
        return createCell(1, 2, "Duration");
    }

    private Cell getTaskTitle() {
        return createCell(1, 8, "Task's Details");
    }

    private Table designTable(Table headerTable) {
        headerTable.setFontColor(ColorConstants.BLACK);
        return headerTable;
    }
}
