package BankingApplication;

import BankingApplicationExecptions.BankingExceptions;
import BankingApplicationExecptions.TransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank;
    Customer customer;
    BankAccount bankAccount;
    BankAccount bankAccount2;
    Customer []customer2= new Customer[2];
    Bank bank2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank2= new Bank(customer2);
        bankAccount2=new BankAccount();

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testThatABankCanHaveCustomerOrCustomers() throws BankingExceptions {
        customer = new Customer("billy1","audu","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        bankAccount = new BankAccount("Current",new BigDecimal(1000));

        Customer customer2 = new Customer("bil1","aud","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        BankAccount bankAccount2 = new BankAccount("Savings",new BigDecimal(1000));

        bank.registerCustomers(customer,bankAccount);
        bank.registerCustomers(customer2,bankAccount2);
        System.out.println(bank.getCustomerAccountAndDetails("current",
                bankAccount.getAccountId().toString()));
        System.out.println(bank.getCustomerAccountAndDetails("savings",
                bankAccount2.getAccountId().toString()));
        System.out.println(bank.getCustomerAccountAndDetails("current",bankAccount2.getAccountId().toString()));
        assertEquals(customer,bank.getCustomers()[0]);


    }

    @Test
    void testThatACustomerIsNotAssignedTheSameTypeOfAccountTwice() throws BankingExceptions {
        customer = new Customer("billy2","audu","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        bankAccount = new BankAccount("Current",new BigDecimal(1000));
        bank.registerCustomers(customer,bankAccount);
        System.out.println(bank.getCustomerAccountAndDetails("Current",bankAccount.getAccountId().toString()));
        Customer customer2 = new Customer("billy2","audu","adams","semicolon,Yaba",
                "billyauduz@gmail.com","09056790434" );
        bankAccount2 = new BankAccount("Current",new BigDecimal(1000));
        bank.registerCustomers(customer2,bankAccount2);
        System.out.println(bank.getCustomerAccountAndDetails("Current",bankAccount2.getAccountId().toString()));
        Customer customer3 = new Customer("billy2","audu","adams","semicolon,Yaba",
                "billyauduz@gmail.com","09056790434" );
       BankAccount bankAccount3 = new BankAccount("Current", new BigDecimal(1000));
        bank.registerCustomers(customer3,bankAccount3);


 }

    @Test
    void testThatTwoCustomersAreNotAssignedTheSameAccountNumber() throws BankingExceptions {
        customer = new Customer("billy3","audu","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        bankAccount = new BankAccount("Savings",new BigDecimal(1000));
        bank.registerCustomers(customer, bankAccount);
//        System.out.println(customer.toString() + bankAccount.toString());

      Customer customer2 = new Customer("bil3","aud","adams","semicolon,Yaba",
                "billyaudus@gmail.com","09056790443" );
       BankAccount bankAccount2 = new BankAccount("Current",new BigDecimal(1000));
        bank.registerCustomers(customer2,bankAccount2);

        System.out.println(bank.getCustomerAccountAndDetails("Current",bankAccount.getAccountId().toString()+"223"));

        assertEquals(customer,bank.getCustomers()[0]);
        assertEquals(customer2, bank.getCustomers()[1]);
    }

    @Test
    void testForPasswordsCanBeSetToAnAccount() throws BankingExceptions {
        customer = new Customer("billy3","audu","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        bankAccount = new BankAccount("Savings",new BigDecimal(1000));
        bank.registerCustomers(customer, bankAccount);
        System.out.println(bankAccount.getAccountId().toString());
         bank.setCustomerPassword(bankAccount.getAccountId().toString(),"12345");


    }
    @Test
    void testThaBasicTransactionsArePossible() throws BankingExceptions, TransactionException {
        Customer billy = new Customer("billy4","audu","adams","semicolon,Yaba",
                "billyaudu@gmail.com","09056790444" );
        bankAccount = new BankAccount("Savings",new BigDecimal(1000));
        bank.registerCustomers(billy,bankAccount);
        bank.setCustomerPassword(bankAccount.getAccountId().toString(),"1234");


        Customer titi = new Customer("Titi4","titus","agba-agba","semi-colon","titi@semi-colon","1234");
        BankAccount bankAccount2 = new BankAccount("Current",new BigDecimal(2000));
        bank.registerCustomers(titi,bankAccount2);



        bank.depositMoney(bankAccount.getAccountId().toString(),new BigDecimal(2000));
        assertEquals(new BigDecimal(3000),bankAccount.getBalance());

        bank.depositMoney(bankAccount.getAccountId().toString()+"JJ",new BigDecimal(2000));
        assertEquals(new BigDecimal(3000),bankAccount.getBalance());

        bank.withdrawal(bankAccount.getAccountId().toString(),new BigDecimal(200),"1234");
        assertEquals(new BigDecimal(2800),bankAccount.getBalance());

        bank.transfer(bankAccount.getAccountId().toString(),bankAccount2.getAccountId().toString(),new BigDecimal(300),"1234");
        assertEquals(new BigDecimal(2500),bankAccount.getBalance());

        bank.transfer(bankAccount.getAccountId().toString(),bankAccount2.getAccountId().toString(),new BigDecimal(300),"123");
        assertEquals(new BigDecimal(2500),bankAccount.getBalance());

        bank.transfer("34436",bankAccount2.getAccountId().toString(),new BigDecimal(300),"1234");
        assertEquals(new BigDecimal(2500),bankAccount.getBalance());

        bank.transfer("SA-"+bankAccount.getAccountId().toString(),bankAccount2.getAccountId().toString(),new BigDecimal(300),"1234");
        assertEquals(new BigDecimal(2200),bankAccount.getBalance());

    }
}