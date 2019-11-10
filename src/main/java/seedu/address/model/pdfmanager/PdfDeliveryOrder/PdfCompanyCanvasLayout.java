package seedu.address.model.pdfmanager.PdfDeliveryOrder;

import java.util.Optional;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Paragraph;
import seedu.address.model.company.Company;
import seedu.address.model.company.GstRegistrationNumber;
import seedu.address.model.company.RegistrationNumber;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class PdfCompanyCanvasLayout extends PdfCanvasLayout {

    private static final float TITLE_FONT_SIZE = 30;
    private static final float SUB_TITLE_FONT_SIZE = 8;

    private Company company;

    public PdfCompanyCanvasLayout(PdfCanvas pdfCanvas, PdfDocument pdfDocument, Rectangle rectangle, Company company) {
        super(pdfCanvas, pdfDocument, rectangle);
        this.company = company;
    }

    public void generate() {
        Paragraph companyName = getCompanyName(company.getName());
        Paragraph companyInformation = getCompanyInformation(company.getAddress(), company.getPhone(),
                company.getFax(), company.getEmail(), company.getRegistrationNumber(), company.getGstRegistrationNumber());

        canvas.add(companyName);
        canvas.add(companyInformation);

        canvas.close();
    }

    private Paragraph getCompanyName(Name name) {
        Paragraph companyName = createParagraph(createName(name), TITLE_FONT_SIZE);
        //styling
        companyName.setFontColor(ColorConstants.RED);
        companyName.setPadding(0);
        companyName.setMargin(0);
        return companyName;
    }

    private Paragraph getCompanyInformation(Address address, Phone phone, Phone fax, Email email,
                                            RegistrationNumber regNo, Optional<GstRegistrationNumber> gstRegNo) {
        StringBuilder companyInfo = new StringBuilder();
        companyInfo.append(createAddress(address)).append("\n")
                .append(createPhone(phone)).append("  ").append(createFax(fax)).append("\n")
                .append(createEmail(email)).append("\n")
                .append(createRegistrationNumber(regNo)).append("  ").append(createGstRegistrationNumber(gstRegNo));

        Paragraph companyInfoParagraph = createParagraph(companyInfo.toString(), SUB_TITLE_FONT_SIZE);
        companyInfoParagraph.setPadding(0);
        companyInfoParagraph.setMargin(0);
        return companyInfoParagraph;
    }

    private String createName(Name name) {
        return name.toString();
    }

    private String createAddress(Address address) {
        return address.toString();
    }

    private String createPhone(Phone phone) {
        return "Tel: " + phone;
    }

    private String createFax(Phone fax) {
        return "Fax: " + fax;
    }

    private String createEmail(Email email) {
        return "Email: " + email;
    }

    private String createRegistrationNumber(RegistrationNumber regNo) {
        return "Co Reg. No.: " + regNo;
    }

    private String createGstRegistrationNumber(Optional<GstRegistrationNumber> gstRegNo) {
        String label = "Co Reg. No.: ";
        String gstRegNoPrint;

        if (gstRegNo.isEmpty()) {
            gstRegNoPrint = label + GstRegistrationNumber.EMPTY_REPRESENTATION;
        } else {
            gstRegNoPrint = label + gstRegNo.get();
        }

        return gstRegNoPrint;
    }
}
