package BankingApplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer1;

    @BeforeEach
    void setUp() {
        customer1 = new Customer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatAllCustomersDetailsAreCorrect(){
        Customer customer1= new Customer("Titus","damilola","Titi","lagos","290030","T@y.com");
        customer1.setFirstName("billy");
        assertEquals("billy", customer1.getFirstName());

        customer1.setLastName("audu");
        assertEquals("audu", customer1.getLastName());

        customer1.setOtherNames("adams");
        assertEquals("adams", customer1.getOtherNames());

        customer1.setAddress("semicolon, yaba, Lagos");
        assertEquals("semicolon, yaba, Lagos", customer1.getAddress());

        customer1.setPhoneNumber("09056790444");
        assertEquals("09056790444", customer1.getPhoneNumber());

        customer1.setEmail("billyaudu@gmail.com");
        assertEquals("billyaudu@gmail.com", customer1.getEmail());


    }
    @Test
    void testCustomer(){
        Customer customer1= new Customer("Titus","damilola","Titi","lagos","290030","T@y.com");
        customer1.setFirstName("billy");
        assertEquals("billy", customer1.getFirstName());


        customer1.setLastName("audu");
        assertEquals("audu", customer1.getLastName());

        customer1.setOtherNames("adams");
        assertEquals("adams", customer1.getOtherNames());

        customer1.setAddress("semicolon, yaba, Lagos");
        assertEquals("semicolon, yaba, Lagos", customer1.getAddress());

        customer1.setPhoneNumber("09056790444");
        assertEquals("09056790444", customer1.getPhoneNumber());

        customer1.setEmail("billyaudu@gmail.com");
        assertEquals("billyaudu@gmail.com", customer1.getEmail());
        System.out.println(customer1.toString());
        System.out.println();
        System.out.println(customer1.toString());

    }
}