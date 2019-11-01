package seedu.address.model.pdfmanager;

import javax.swing.text.StyleConstants;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfPageHeaderLayout extends PdfLayout {

    private Document document;

    public PdfPageHeaderLayout(Document document) {
        this.document = document;
    }

    public void createPageHeader() {
        Paragraph headerParagraph = getPageHeader();
        document.add(designParagraph(headerParagraph));
    }

    private Paragraph getPageHeader() {
        return createParagraph("Deliveria");
    }

    private Paragraph designParagraph(Paragraph paragraph) {
        paragraph.setFontSize(36);
        paragraph.setFontColor(ColorConstants.RED);

        return paragraph;
    }
}
