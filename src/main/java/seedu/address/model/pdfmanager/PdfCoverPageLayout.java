package seedu.address.model.pdfmanager;

import java.io.IOException;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

public class PdfCoverPageLayout extends PdfLayout {

    public static final float PAGE_WIDTH =  PageSize.A4.getWidth();
    public static final float PAGE_HEIGHT =  PageSize.A4.getHeight();

    private Document document;

    public PdfCoverPageLayout(Document document) {
        super();
        this.document = document;
    }

    public void addCoverPage(String title, String subTitle) throws IOException {
        Paragraph titleParagraph = createTitle(title);
        Paragraph subTitleParagraph = createSubTitle(subTitle);
        document.add(alignParagraphMiddle(titleParagraph));
        document.add(alignParagraphMiddle(subTitleParagraph));

        Table sampleTable = createSampleTable();
        document.add(sampleTable);
    }

    public Paragraph createTitle(String title) {
        Text titleText = new Text(title);
        titleText.setFontColor(ColorConstants.RED);
        titleText.setFontSize(48);
        titleText.setBold();

        Paragraph titleParagraph = new Paragraph(titleText);
        titleParagraph.setFixedPosition(60, 550, 500);

        return titleParagraph;
    }

    public Paragraph createSubTitle(String subTitle) {
        Text subTitleText = new Text(subTitle);
        subTitleText.setFontColor(ColorConstants.BLUE);
        subTitleText.setItalic();

        Paragraph subTitleParagraph = new Paragraph(subTitleText);
        subTitleParagraph.setFixedPosition(60, 530, 500);

        return subTitleParagraph;
    }

    public Table createSampleTable() {
        PdfSampleLayout sampleLayout = new PdfSampleLayout();
        Table sampleTable = sampleLayout.createTable();
        sampleTable.setFixedPosition(60, 200, sampleTable.getWidth());

        return sampleTable;
    }
}
