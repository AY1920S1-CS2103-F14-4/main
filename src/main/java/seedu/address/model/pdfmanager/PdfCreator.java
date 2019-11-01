package seedu.address.model.pdfmanager;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.pdfmanager.exceptions.PdfNoTaskToDisplayException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.task.Task;

/**
 * Creates and saves details provided into a pdf file.
 */
public class PdfCreator {

    public static final Customer VALID_CUSTOMER = new Customer(1, new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@gmail.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            new HashSet<>());

    public static final Driver VALID_DRIVER = new Driver(1, new Name("Aloysius Chan"),
            new Phone("92837163"), new Email("aloysius@gmail.com"),
            new Address("Blk 123 Bukit Panjang Street 10, #11-04"),
            new HashSet<>());

    public final String filePath;

    public PdfCreator(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Saves drivers` tasks for a specific date into a pdf file.
     *
     * @param tasks tasks list.
     * @param dateOfDelivery date of delivery.
     * @throws IOException if directory used for saving is not found.
     */
    public void saveDriverTaskPdf(List<Task> tasks, LocalDate dateOfDelivery)
            throws IOException, PdfNoTaskToDisplayException {
        Document document = createDocument(filePath);
        insertCoverPage(document, dateOfDelivery);
        insertDriverTask(document, tasks, dateOfDelivery);

        //close to save
        document.close();
    }

    private void createFileIfMissing() throws IOException {
        FileUtil.createIfMissing(Paths.get(getFilePath()));
    }

    private Document createDocument(String filePath) throws IOException {
        createFileIfMissing();
        PdfDocument pdf = new PdfDocument(new PdfWriter(filePath));
        Document newDocument = new Document(pdf);
        newDocument.setMargins(30,30,30, 30);
        return newDocument;
    }

    private void insertCoverPage(Document document, LocalDate dateOfDelivery) throws IOException {
        //add cover page
        String title = "Deliveria";
        String subTitle = "Delivery Tasks for " + dateOfDelivery;

        PdfCoverPageLayout coverPageLayout = new PdfCoverPageLayout(document);
        coverPageLayout.addCoverPage(title, subTitle);
    }

    private void insertDriverTask(Document document, List<Task> tasks, LocalDate dateOfDelivery)
            throws PdfNoTaskToDisplayException {
        //get all the tasks in pdf layout
        PdfWrapperLayout wrapperLayout = new PdfWrapperLayout(document);
        wrapperLayout.populateDocumentWithTasks(tasks, dateOfDelivery);
    }
}
