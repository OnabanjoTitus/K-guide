package BankingApplication;

public class BankVerifications {
    private String customerPin;

    public BankVerifications(String customerPin) {
        this.customerPin = customerPin;
    }

    public BankVerifications() {
    }

    public String getCustomerPin() {
        return customerPin;
    }

    public void setCustomerPin(String customerPin) {
        this.customerPin = customerPin;
    }
}