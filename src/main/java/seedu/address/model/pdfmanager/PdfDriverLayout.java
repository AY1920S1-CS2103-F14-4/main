package seedu.address.model.pdfmanager;

import java.time.LocalDate;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class PdfDriverLayout extends PdfLayout {

    private Driver driver;
    private Document document;
    private LocalDate dateOfDelivery;

    public PdfDriverLayout(Document document, Driver driver, LocalDate dateOfDelivery) {
        this.driver = driver;
        this.document = document;
        this.dateOfDelivery = dateOfDelivery;
    }

    public void createDriverDetails() {
        //populate Paragraph
        Paragraph dateParagraph = getDateOfDelivery(dateOfDelivery);
        Paragraph driverIdParagraph = getDriverIdCell(driver.getId());
        Paragraph nameParagraph = getNameCell(driver.getName());
        Paragraph phoneNumberParagraph = getPhoneNumberCell(driver.getPhone());

        document.add(dateParagraph);
        document.add(driverIdParagraph);
        document.add(nameParagraph);
        document.add(phoneNumberParagraph);
    }

    private Paragraph getDriverIdCell(int id) {
        String idStr = "Driver ID: " + id;
        return createParagraph(idStr);
    }

    private Paragraph getNameCell(Name name) {
        String nameStr = "Driver: " + name;
        return createParagraph(nameStr);
    }

    private Paragraph getPhoneNumberCell(Phone phone) {
        String phoneStr = "Contact No: " + phone;
        return createParagraph(phoneStr);
    }

    private Paragraph getDateOfDelivery(LocalDate date) {
        String dateOfDelivery = "Date of Delivery: " + date;
        return createParagraph(dateOfDelivery);
    }
}
