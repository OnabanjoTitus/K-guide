package BankingApplication;

import BankingApplicationExecptions.BankingExceptions;
import BankingApplicationExecptions.TransactionException;

import java.math.BigDecimal;
import java.util.*;

public class Bank extends BankVerifications implements Transactions{


    private Customer[] customers = new Customer[10];
    private String [] bankVerifications= new String[10];
    int filledLength=1;

    private final BankAccount bankAccountNotFound= new BankAccount();

    ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public Bank() {

    }

    public Bank(Customer[] customer) {
        this.customers = customer;

    }


    public Customer[] getCustomers() {
        Customer[] status = null;


            status = customers;

        return status;


    }

    public boolean registerCustomers(Customer customer,BankAccount bankAccount ) {
          boolean message=false;
        for (int counter = 0; counter < customers.length; counter++) {
              if(customers[0]==null){
                  customers[0]=customer;
                  bankAccounts.add(bankAccount);
                  message=true;
                  break;

              }
              if (customers[counter] == null) {
                  boolean status=isAnExistingCustomerAccount(customer);
                 if(!status){
                  customers[counter] = customer;
                  bankAccounts.add(bankAccount);
                  filledLength+=1;
                  message=true;
                     break;
                 }

                  break;
              }
          }
    return message;
    }

    private boolean isAnExistingCustomerAccount(Customer customer) {
        boolean status=false;
        for(int counter=0; counter<filledLength;counter++){
            if(customers[counter].getPhoneNumber().equals(customer.getPhoneNumber())||
                    customers[counter].getEmail().equals(customer.getEmail())){
                status=true;
                break;
            }
        }
        return status;
 }

    public String getCustomerAccountAndDetails(String accountType,String accountId) throws BankingExceptions {

        String status="Customer Not found";
       String accountIdentity = IdSorter(accountId);
         BankAccount bankAccount =  bankAccounts.stream()
                .filter(accountProfile -> accountIdentity.equals(accountProfile.getAccountId().toString()))
                 .findFirst().orElse(bankAccountNotFound);

      int counter = bankAccounts.indexOf(bankAccount);
      if(counter>=0){
          if(customerAccountTypeVerification(accountType,bankAccount)){
    status=customers[counter].toString()+bankAccount.toString();}
      else{
          throw new BankingExceptions("This customer is not found");
          }
      }

    return status;
    }

    public void setCustomerPassword(String accountId,String password) throws BankingExceptions {

        BankAccount bankAccount =  bankAccounts.stream()
                .filter(accountProfile -> accountId.equals(accountProfile.getAccountId().toString()))
                .findFirst().orElse(bankAccountNotFound);

        int counter = bankAccounts.indexOf(bankAccount);
        if(counter>=0){
            if(password.length()==4){
        bankVerifications[counter]=password;
        }
            else {
               throw new BankingExceptions("Incorrect Password Length(Must Be Of Length 4)");
            }
        }

    }
    private boolean customerAccountTypeVerification(String accountType, BankAccount bankAccount) {
        boolean confirmation = false;
        if(accountType.equalsIgnoreCase(bankAccount.getAccountTypes().toString())){
            confirmation=true;
        }
        return confirmation;
    }



    private String IdSorter(String accountId) {
        String filteredId=accountId;
        boolean preceding=false;
        if(accountId.startsWith("CU-")||
                accountId.startsWith("SA-")){
            preceding=true;
        }

        if(preceding){
           StringBuilder accountIdTrimmed= new StringBuilder(accountId);
           accountIdTrimmed.delete(0,3);
           filteredId=accountIdTrimmed.toString();
        }
        return filteredId;
    }






    @Override
    public void depositMoney(String accountId, BigDecimal amount) throws TransactionException {
        accountId = IdSorter(accountId);
        String finalAccountId = accountId;
        BankAccount bankAccount =  bankAccounts.stream()
                .filter(accountProfile -> finalAccountId.equals(accountProfile.getAccountId().toString()))
                .findFirst().orElse(bankAccountNotFound);
        if(bankAccount!=bankAccountNotFound){
        bankAccount.setDepositMoney(amount);
        }
        else {
            throw new TransactionException("Account Not Found");
        }

    }

    @Override
    public void transfer(String senderAccountId, String receiverAccountId, BigDecimal amount, String password) throws TransactionException {

        String accountIdOne = IdSorter(senderAccountId);
        String accountIdTwo = IdSorter(receiverAccountId);
        BankAccount bankAccount =  bankAccounts.stream()
                .filter(accountProfile -> accountIdOne.equals(accountProfile.getAccountId().toString()))
                .findFirst().orElse(bankAccountNotFound);
        BankAccount bankAccount2 =  bankAccounts.stream()
                .filter(accountProfile -> accountIdTwo.equals(accountProfile.getAccountId().toString()))
                .findFirst().orElse(bankAccountNotFound);
        int count=bankAccounts.indexOf(bankAccount);
        if(bankAccount!=bankAccountNotFound && bankAccount2!=bankAccountNotFound){
            if(bankVerifications[count].equals(password)){
                bankAccount.setWithdrawMoney(amount);
                bankAccount2.setDepositMoney(amount);
                }
            }else {
               throw new TransactionException("Transfer Failed");
            }
    }

    @Override
    public void withdrawal(String accountId, BigDecimal amount, String password) throws TransactionException {
        String status="IncorrectPassword";
        String accountIdentity = IdSorter(accountId);
        BankAccount bankAccount =  bankAccounts.stream()
                .filter(accountProfile -> accountIdentity.equals(accountProfile.getAccountId().toString()))
                .findFirst().orElse(bankAccountNotFound);
        int count=bankAccounts.indexOf(bankAccount);
        if(bankVerifications[count].equals(password))
        {
            bankAccount.setWithdrawMoney(amount);
}else {
            throw new TransactionException("Withdrawal Failed");
        }

    }
}