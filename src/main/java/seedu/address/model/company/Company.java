package seedu.address.model.company;

import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Represents a company or an organisation.
 */
public class Company {

    private Name name;
    private Address address;
    private Phone phone;
    private Phone fax;
    private Email email;
    private RegistrationNumber registrationNumber;
    private Optional<GstRegistrationNumber> gstRegistrationNumber;

    public Company(Name companyName, Address companyAddress, Phone companyPhone, Phone companyFax,
                   Email companyEmail, RegistrationNumber registrationNumber,
                   Optional<GstRegistrationNumber> gstRegistrationNumber) {
        this.name = companyName;
        this.address = companyAddress;
        this.phone = companyPhone;
        this.fax = companyFax;
        this.email = companyEmail;
        this.registrationNumber = registrationNumber;
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Phone getPhone() {
        return phone;
    }

    public Phone getFax() {
        return fax;
    }

    public Email getEmail() {
        return email;
    }

    public RegistrationNumber getRegistrationNumber() {
        return registrationNumber;
    }

    public Optional<GstRegistrationNumber> getGstRegistrationNumber() {
        return gstRegistrationNumber;
    }

    public void setName(Name companyName) {
        this.name = companyName;
    }

    public void setAddress(Address companyAddress) {
        this.address = companyAddress;
    }

    public void setPhone(Phone companyPhone) {
        this.phone = companyPhone;
    }

    public void setFax(Phone companyFax) {
        this.fax = companyFax;
    }

    public void setEmail(Email companyEmail) {
        this.email = companyEmail;
    }

    public void setRegistrationNumber(RegistrationNumber registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setGstRegistrationNumber(Optional<GstRegistrationNumber> gstRegistrationNumber) {
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    @Override
    public String toString() {
        StringBuilder companyStr = new StringBuilder();
        return companyStr.append("Company Name: ")
                .append(getName())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tel No.: ")
                .append(getPhone())
                .append(" Fax No.: ")
                .append(getFax())
                .append(" Email: ")
                .append(getEmail())
                .append(" Co. Reg. No.: ")
                .append(getRegistrationNumber())
                .append(" GST Reg. No.:")
                .append(getGstRegistrationNumber())
                .toString();
    }
}
