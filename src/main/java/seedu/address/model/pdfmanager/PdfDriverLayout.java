package seedu.address.model.pdfmanager;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import seedu.address.model.person.Driver;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class PdfDriverLayout extends PdfTable {

    private Driver driver;

    public PdfDriverLayout(Driver driver) {
        this.driver = driver;
    }

    public Table createTable() {
        //setting basic layout
        Table driverTable = new Table(UnitValue.createPercentArray(10)).useAllAvailableWidth().setFixedLayout();

        //populate cells
        Cell driverIdCell = getDriverIdCell(driver.getId());
        Cell nameCell = getNameCell(driver.getName());
        Cell phoneNumberCell = getPhoneNumberCell(driver.getPhone());

        driverTable.addCell(driverIdCell);
        driverTable.addCell(nameCell);
        driverTable.addCell(phoneNumberCell);

        return driverTable;
    }

    private Cell getDriverIdCell(int id) {
        String idStr = "Driver ID: " + id;
        return createAndPopulate(1, 2, idStr);
    }

    private Cell getNameCell(Name name) {
        String nameStr = "Name: " + name;
        return createAndPopulate(1, 5, nameStr);
    }

    private Cell getPhoneNumberCell(Phone phone) {
        String phoneStr = "Phone: " + phone;
        return createAndPopulate(1, 3,phoneStr);
    }

}
