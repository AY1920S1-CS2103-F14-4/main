package seedu.address.model.pdfmanager.pdfdeliveryorder;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents the canvas that contains the customer information.
 */
public class PdfCustomerCanvasLayout extends PdfCanvasLayout {

    private Customer customer;

    public PdfCustomerCanvasLayout(PdfCanvas pdfCanvas, PdfDocument pdfDocument, Rectangle rectangle,
                                   Customer customer) {
        super(pdfCanvas, pdfDocument, rectangle);
        this.customer = customer;
    }

    /**
     * Generates a layout of the customer information.
     */
    public void generate() {
        Table table = new Table(1);
        Cell title = createTitle("Delivery Address:");
        Cell address = createAddress(customer.getAddress());
        Cell customerInformation = createCustomerInformation(customer.getName(), customer.getPhone());

        table.addCell(title);
        table.addCell(address);
        table.addCell(customerInformation);
        insertBorder();

        canvas.add(table);

        canvas.close();
    }

    /**
     * Creates customer information title layout.
     */
    private Cell createTitle(String title) {
        Cell titleCell = createCell(1, 1, title);

        //styling
        titleCell.setItalic();
        titleCell.setFontColor(ColorConstants.LIGHT_GRAY);
        setPaddingLeftRight(titleCell);

        return titleCell;
    }

    /**
     * Creates delivery address layout.
     */
    private Cell createAddress(Address address) {
        Cell addressCell = createCell(1, 1, address.toString());
        //styling
        addressCell.setBold();
        addressCell.setUnderline();
        addressCell.setHeight(40);
        addressCell.setBorder(Border.NO_BORDER);
        setPaddingLeftRight(addressCell);

        return addressCell;
    }

    /**
     * Creates customer information layout.
     */
    private Cell createCustomerInformation(Name name, Phone phone) {
        StringBuilder customerInfo = new StringBuilder();
        customerInfo.append(createName(name)).append("\n")
                .append(createPhone(phone));
        Cell customerCell = createCell(1, 1, customerInfo.toString());

        //styling
        setPaddingLeftRight(customerCell);
        customerCell.setBorder(Border.NO_BORDER);

        return customerCell;
    }

    private String createName(Name name) {
        return "Attn: " + name;
    }

    private String createPhone(Phone phone) {
        return "Tel No.: " + phone;
    }

    private void setPaddingLeftRight(Cell cell) {
        cell.setPaddingLeft(5);
        cell.setPaddingRight(5);
    }
}
