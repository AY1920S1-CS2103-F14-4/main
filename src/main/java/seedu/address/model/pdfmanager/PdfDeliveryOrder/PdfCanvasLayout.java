package seedu.address.model.pdfmanager.PdfDeliveryOrder;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;

public abstract class PdfCanvasLayout {

    protected PdfCanvas pdfCanvas;
    protected PdfDocument pdfDocument;
    protected Rectangle contentHolder;
    protected Canvas canvas;

    public PdfCanvasLayout(PdfCanvas pdfCanvas, PdfDocument pdfDocument, Rectangle rectangle) {
        //Position X and Y (0,0) refers to the bottom left point.
        this.pdfCanvas = pdfCanvas;
        this.pdfDocument = pdfDocument;
        this.contentHolder = rectangle;
        canvas = new Canvas(pdfCanvas, pdfDocument, contentHolder);
    }

    /**
     * Generates a canvas filled with content.
     */
    public abstract void generate();

    public Paragraph createParagraph(String message, float fontSize) {
        Paragraph paragraph = new Paragraph(message);
        paragraph.setFontSize(fontSize);
        return paragraph;
    }
}
