package BankingApplication;


public class Customer {
    private String firstName;
    private String lastName;
    private String otherNames;
    private String address;
    private String email;
    private String phoneNumber;

    public Customer() {
    }
    public Customer(String firstName, String lastName, String otherNames, String address, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("Customer{%n" +
                "firstName='" + firstName + '\'' +
                "%nlastName='" + lastName + '\'' +
                "%notherNames='" + otherNames + '\'' +
                "%naddress='" + address + '\'' +
                "%nemail='" + email + '\'' +
                "%nphoneNumber='" + phoneNumber + '\''
                );
    }
}
