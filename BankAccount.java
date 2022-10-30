package BankingApplication;


import BankingApplicationExecptions.TransactionException;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    private BigDecimal withdrawAmount;
    private BigDecimal depositAmount;
    private Enum accountTypes;
    private UUID accountId;
    private BigDecimal accountBalance=new BigDecimal(0);




    public BankAccount() {
    }

    public UUID getAccountId() {
        return accountId;
    }
    public BankAccount(String accountTypes) {
        setAccountTypes(accountTypes);
        this.accountId = generateAccountNumber();
    }
    public BankAccount(String accountTypes,String accountId) {
    }

    public BankAccount(String accountTypes,BigDecimal depositAmount) {
        setAccountTypes(accountTypes);
        this.accountId = generateAccountNumber();
        this.accountBalance = depositAmount;
    }

    private UUID generateAccountNumber() {
        UUID accountNumber = null;
        if (accountTypes==AccountTypes.SAVINGS) {
            accountNumber =  UUID.randomUUID();

        }
        if (accountTypes==AccountTypes.CURRENT){
            accountNumber = UUID.randomUUID();
    }
        return accountNumber;
}

    public BigDecimal getWithdrawMoney() {
        return withdrawAmount;
    }public void setWithdrawMoney(BigDecimal withdrawAmount) throws TransactionException {

        this.withdrawAmount = withdrawAmount;
        if(accountBalance.compareTo(withdrawAmount) > 0){
        accountBalance=( accountBalance.subtract( withdrawAmount));

        }
        else {
           throw new TransactionException("Withdrawal Failed Due To Insufficient Funds");
        }

    }

    public BigDecimal getDepositMoney() {
        return depositAmount;
    }
    public void setDepositMoney(BigDecimal depositAmount) throws TransactionException {

        if(depositAmount.compareTo(new BigDecimal(0))>0){this.depositAmount = depositAmount;
         accountBalance =accountBalance.add(depositAmount);}
        else {
            throw new TransactionException("Invalid Deposit Amount");
        }

    }


    public BigDecimal getBalance() {
        return accountBalance;
    }

    public void setAccountTypes(String accountTypes) {
       accountTypes = accountTypes.toLowerCase();
        switch (accountTypes){
            case  "savings" -> {this.accountTypes=AccountTypes.SAVINGS;
                                 this.accountId = generateAccountNumber();
            }
            case "current" ->{this.accountTypes=AccountTypes.CURRENT;
                this.accountId = generateAccountNumber(); }}
    }
    public Enum getAccountTypes() {
        return accountTypes;
    }

    @Override
    public String toString() {
        return String.format("%nYour account balance is %.2f%nAnd your account type is a %s account%nThe account Id is %s%s%n }",
                getBalance(),getAccountTypes(),(accountTypes==AccountTypes.SAVINGS?"SA-":"CU-"), getAccountId());
    }
}


