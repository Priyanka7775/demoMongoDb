package com.example.demoMongoDb.repository;

import com.example.demoMongoDb.domain.Address;
import com.example.demoMongoDb.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Address address;
    private Customer customer;

    @BeforeEach
    public void setup(){
        address=new Address("delhi","New delhi","India","565656");
        customer=new Customer(1,"priyanka","p@gmail.com",address);
    }

    @AfterEach
    void tearDown(){
       address=null;
        customer=null;
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Test case for saving customer object")
    void givenCustomerToSaveShouldReturnSavedCustomer(){
        customerRepository.save(customer);
        Customer customer1=customerRepository.findById(customer.getCustid()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustid(),customer1.getCustid());
    }


//    @Test
//    @DisplayName("Test case for deleting customer object")
//    public void givenCustomerToDeleteShouldDeleteCustomer() {
//        // customerRepository.insert(customer);
//        Customer customer1 = customerRepository.findById(customer.getCustid()).get();
//        customerRepository.delete(customer1);
//        assertEquals(Optional.empty(), customerRepository.findById(customer.getCustid()));
//    }


    @Test
    @DisplayName("Test Case for retrieving all the customer object")
    void givenCustomerReturnAllCustomerDetail(){
        customerRepository.insert(customer);
        address=new Address("Raipur","CG","India","78898");
        customer=new Customer(2,"Neha","n@gmail.com",address);
        customerRepository.insert(customer);

        List<Customer> list = customerRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Neha", list.get(1).getCustname());

    }
    @Test
    @DisplayName("Test case for deleting customer object")
    public void givenCustomerToDeleteShouldDeleteCustomer() {
         customerRepository.insert(customer);
        Customer customer1 = customerRepository.findById(customer.getCustid()).get();
        customerRepository.delete(customer1);
        assertEquals(Optional.empty(), customerRepository.findById(customer.getCustid()));
    }




}
