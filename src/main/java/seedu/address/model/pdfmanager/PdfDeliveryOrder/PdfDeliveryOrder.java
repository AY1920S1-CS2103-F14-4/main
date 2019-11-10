package seedu.address.model.pdfmanager.PdfDeliveryOrder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.BorderRadius;
import seedu.address.model.company.Company;
import seedu.address.model.task.Task;

/**
 * Represents a list of delivery orders in PDF format.
 */
public class PdfDeliveryOrder {

    //A4 size is 595 x 842
    private static final Rectangle COMPANY_HOLDER = new Rectangle(30, 680, 500, 150);

    private PdfDocument pdfDocument;
    private List<Task> tasks;
    private Company company;

    public PdfDeliveryOrder(PdfDocument pdfDocument, List<Task> tasks, Company company) {
        this.pdfDocument = pdfDocument;
        this.tasks = tasks;
        this.company = company;
    }

    public void create() {
        for (Task task : tasks) {
            PdfPage newPage = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(newPage);

            List<PdfCanvasLayout> allCanvas = List.of(
                    new PdfCompanyCanvasLayout(pdfCanvas, pdfDocument, COMPANY_HOLDER, company));

            allCanvas.forEach(PdfCanvasLayout::generate);

        }

    }
}
