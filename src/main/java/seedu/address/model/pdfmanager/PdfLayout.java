package seedu.address.model.pdfmanager;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

public class PdfLayout {

    public PdfLayout() {

    }

    public Cell createCell(int rowSpan, int colSpan, String message) {
        Cell newCell = new Cell(rowSpan, colSpan).add(new Paragraph(message));
        Cell designedCell = alignCellMiddle(newCell);
        return designedCell;
    }

    public Paragraph createParagraph(String str) {
        Paragraph paragraph = new Paragraph(str);
        return paragraph;
    }

    public Paragraph alignParagraphMiddle(Paragraph paragraph) {
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setVerticalAlignment(VerticalAlignment.MIDDLE);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return paragraph;
    }

    public Cell alignCellMiddle(Cell cell) {
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.setMargin(0);
        cell.setPadding(5);
        return cell;
    }

    public Cell boldCell(Cell cell) {
        cell.setBold();
        return cell;
    }

    public Cell insertPadding(Cell cell) {
        cell.setPadding(20);
        return cell;
    }
}
