package BankingApplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankingHallTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void customerCreation() {
     Customer customer =   BankingHall.customerCreation("Onyie","agbo","eliza","benue",
                "madam@yahoo.com","080123455");
        assertNotNull(customer);
    }

    @Test
    void bankAccount() {
        BigDecimal amount = new BigDecimal(200);
        BankAccount bankAccount= BankingHall.bankAccount("Savings",amount);
        assertNotNull(bankAccount);
    }
}