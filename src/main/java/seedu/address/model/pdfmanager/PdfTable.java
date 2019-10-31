package seedu.address.model.pdfmanager;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.UnitValue;

public class PdfTable {

    public PdfTable() {

    }

    protected Cell createAndPopulate(int rowSpan, int colSpan, String message) {
        Cell newCell = new Cell(rowSpan, colSpan).add(new Paragraph(message));
        newCell.setPadding(5);
        return newCell;
    }

    protected float getTableWidth(Document document) {
        float maxWidth = document.getPdfDocument().getDefaultPageSize().getWidth()
                - document.getLeftMargin() - document.getRightMargin();
        return maxWidth;
    }
}
