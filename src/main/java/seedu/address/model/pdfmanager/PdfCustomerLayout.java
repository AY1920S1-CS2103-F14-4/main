package seedu.address.model.pdfmanager;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class PdfCustomerLayout extends PdfTable {

    private Customer customer;

    public PdfCustomerLayout(Customer customer) {
        super();
        this.customer = customer;
    }

    public Table createTable() {
        //setting the basic layout
        Table customerTable = new Table(10).useAllAvailableWidth();

        //populate the table
        Cell customerIdCell = getCustomerIdCell(customer.getId());
        Cell nameCell = getNameCell(customer.getName());
        Cell phoneNumberCell = getPhoneNumberCell(customer.getPhone());
        Cell addressCell = getAddressCell(customer.getAddress());
        customerTable.addCell(customerIdCell);
        customerTable.addCell(nameCell);
        customerTable.addCell(phoneNumberCell);
        customerTable.addCell(addressCell);

        return customerTable;
    }

    private Cell getCustomerIdCell(int customerId) {
        String idStr = "Customer ID: \n" + customerId;
        return createAndPopulate(1,2, idStr);
    }

    private Cell getNameCell(Name name) {
        String nameStr = "Name: \n" + name;
        return createAndPopulate(1, 4, nameStr);
    }

    private Cell getPhoneNumberCell(Phone phone) {
        String phoneNumberStr = "Phone Number: \n" + phone;
        return createAndPopulate(1,4, phoneNumberStr);
    }

    private Cell getAddressCell(Address address) {
        String addressStr = "Address: " + address;
        return createAndPopulate(1,10, addressStr);
    }
}
