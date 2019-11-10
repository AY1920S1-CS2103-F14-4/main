package seedu.address.model.pdfmanager.pdfdeliveryorder;

import java.util.List;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import seedu.address.model.company.Company;
import seedu.address.model.task.Task;

/**
 * Represents a list of delivery orders in PDF format.
 */
public class PdfDeliveryOrder {

    //A4 size is 595 x 842
    public static final Rectangle COMPANY_HOLDER = new Rectangle(30, 720, 450, 120);
    public static final Rectangle CUSTOMER_HOLDER = new Rectangle(30, 580, 260, 120);

    private PdfDocument pdfDocument;
    private List<Task> tasks;
    private Company company;

    public PdfDeliveryOrder(PdfDocument pdfDocument, List<Task> tasks, Company company) {
        this.pdfDocument = pdfDocument;
        this.tasks = tasks;
        this.company = company;
    }

    /**
     * Creates delivery orders for the delivery tasks.
     */
    public void create() {
        for (Task task : tasks) {
            PdfPage newPage = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(newPage);

            List<PdfCanvasLayout> allCanvas = List.of(
                    new PdfCompanyCanvasLayout(pdfCanvas, pdfDocument, COMPANY_HOLDER, company),
                    new PdfCustomerCanvasLayout(pdfCanvas, pdfDocument, CUSTOMER_HOLDER, task.getCustomer()));

            allCanvas.forEach(PdfCanvasLayout::generate);

        }

    }
}
