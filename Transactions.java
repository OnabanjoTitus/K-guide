package BankingApplication;

import BankingApplicationExecptions.TransactionException;

import java.math.BigDecimal;

public interface Transactions {
    void  depositMoney(String accountId, BigDecimal amount) throws TransactionException;
    void  transfer(String senderAccountId,String receiverAccountId,BigDecimal amount,String password) throws TransactionException;
    void withdrawal(String accountId,BigDecimal amount, String password) throws TransactionException;
}
