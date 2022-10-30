package BankingApplication;

import BankingApplicationExecptions.TransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount;
    BankAccount bankAccount2;
    BankAccount bankAccount3;
    BankAccount bankAccount4;

    @BeforeEach
    void setUp() {
        bankAccount4= new BankAccount("savings",new BigDecimal(100));
        bankAccount = new BankAccount();
        bankAccount2= new BankAccount("SAVINGS");
        bankAccount3 = new BankAccount("Savings","111");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatAllGettersAndSettersAreWorking() throws TransactionException {
        bankAccount.setDepositMoney(new BigDecimal(100));
        assertEquals(new BigDecimal(100), bankAccount.getDepositMoney());
        assertEquals(new BigDecimal(100), bankAccount.getBalance());
        bankAccount.setWithdrawMoney(new BigDecimal(10));
        assertEquals(new BigDecimal(10), bankAccount.getWithdrawMoney());
        assertEquals(new BigDecimal(90), bankAccount.getBalance());
        assertThrows(TransactionException.class,()->bankAccount.setWithdrawMoney(new BigDecimal(10000)));
        assertThrows(TransactionException.class,()->bankAccount.setDepositMoney(new BigDecimal(-10000)));

    }
    @Test
    void testThatToStringMethodWorks(){
        bankAccount.setAccountTypes("Savings");
        assertNotNull(bankAccount.getAccountId());
        bankAccount.setAccountTypes("Current");
        assertNotNull(bankAccount.getAccountId());
        assertEquals(AccountTypes.CURRENT,bankAccount.getAccountTypes());
        System.out.println(bankAccount.toString());
    }

}