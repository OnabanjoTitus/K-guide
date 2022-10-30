package BankingApplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankVerificationsTest {

BankVerifications verifications;
BankVerifications verifications2;
    @BeforeEach
    void setUp() {
        verifications2= new BankVerifications("234");
        verifications=new BankVerifications();

    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testForBankVerifications(){
     verifications.setCustomerPin("123");
     assertEquals("123",verifications.getCustomerPin());
     verifications2.setCustomerPin("23456");
     assertEquals("23456",verifications2.getCustomerPin());
    }

}