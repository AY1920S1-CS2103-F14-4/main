package seedu.address.model.pdfmanager;

import static seedu.address.logic.commands.SavePdfCommand.PDF_DELIVERY_ORDER;
import static seedu.address.logic.commands.SavePdfCommand.PDF_SUMMARY;

import java.util.List;

public class PdfManager {

    public static final String MESSAGE_CONSTRAINTS = "There's only 2 document types: `" + PDF_SUMMARY + "' and `"
            + PDF_DELIVERY_ORDER + "'. \n"
            + PDF_SUMMARY + " - generates a Pdf Task Summary.\n"
            + PDF_DELIVERY_ORDER + " - generates a Pdf Delivery order.";
    private static final List<String> documentTypes = List.of(PDF_SUMMARY, PDF_DELIVERY_ORDER);

    public static boolean isValidDocument(String documentType) {
        return documentTypes.contains(documentType);
    }
}
