package seedu.address.model.pdfmanager;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class PdfCustomerLayout extends PdfLayout {

    private Customer customer;

    public PdfCustomerLayout(Customer customer) {
        super();
        this.customer = customer;
    }

    public Table createTable() {
        //setting the basic layout
        Table customerTable = new Table(10).useAllAvailableWidth().setFixedLayout();

        //populate the table
        Cell customerIdCell = getCustomerIdCell(customer.getId());
        Cell nameCell = getNameCell(customer.getName());
        Cell phoneNumberCell = getPhoneNumberCell(customer.getPhone());
        Cell addressCell = getAddressCell(customer.getAddress());
        customerTable.addCell(customerIdCell);
        customerTable.addCell(nameCell);
        customerTable.addCell(phoneNumberCell);
        customerTable.addCell(addressCell);

        Table designedCustomerTable = designTable(customerTable);
        return designedCustomerTable;
    }

    private Cell getCustomerIdCell(int customerId) {
        String idStr = "Customer ID \n" + customerId;
        return createCell(1,2, idStr);
    }

    private Cell getNameCell(Name name) {
        String nameStr = "Customer\n" + name;
        return createCell(1, 6, nameStr);
    }

    private Cell getPhoneNumberCell(Phone phone) {
        String phoneNumberStr = "Contact No \n" + phone;
        return createCell(1,3, phoneNumberStr);
    }

    private Cell getAddressCell(Address address) {
        String addressStr = "Address: " + address;
        return createCell(1,10, addressStr);
    }

    private Table designTable(Table customerTable) {
        return customerTable;
    }
}
