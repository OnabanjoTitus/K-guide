package BankingApplication;

import java.math.BigDecimal;

public class BankingHall {

    public static Customer customerCreation(String firstname,String lastname, String otherNames, String address, String email,String phoneNumber ){
        Customer customer = new Customer(firstname,lastname,otherNames,address,email,phoneNumber);
        return customer;
    }

    public static BankAccount bankAccount(String accountType, BigDecimal initialDeposit) {
        BankAccount bankAccount = new BankAccount(accountType,initialDeposit);
        return bankAccount;

    }
}
