package com.example.demoMongoDb.services;

import com.example.demoMongoDb.domain.Address;
import com.example.demoMongoDb.domain.Customer;
import com.example.demoMongoDb.exception.CustomerAlreadyExistsException;
import com.example.demoMongoDb.exception.CustomerNotFoundException;
import com.example.demoMongoDb.repository.CustomerRepository;
import com.example.demoMongoDb.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer cust1,cust2;
    List<Customer> listCustomer;
    Address address1,address2;

    @BeforeEach
    public void setup(){
        address1=new Address("Raipur","CG","India","87978");
        address2=new Address("Indore","MP","India","99978");
        cust1=new Customer(1,"Sunidhi","s@gmail.com",address1);
        cust2=new Customer(2,"nidhi","n@gmail.com",address2);

        listCustomer = Arrays.asList(cust1,cust2);
       // listCustomer=customerRepository.findAll();

    }
    @Test
    public void savedatacust() throws CustomerNotFoundException {
       // when(customerRepository.findById(cust1.getCustid())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(cust1)).thenReturn(cust1);
        assertEquals(cust1,customerService.saveCustomer(cust1));
        verify(customerRepository,times(1)).save(cust1);
       // verify(customerRepository,times(1)).findById(any());
    }

    @Test
    public void givenCustomerToDeleteShouldDeleteSuccess() throws CustomerNotFoundException {
        when(customerRepository.findById(cust1.getCustid())).thenReturn(Optional.ofNullable(cust1));
        boolean flag = customerService.deleteCustomer(cust1.getCustid());
        assertEquals(true,flag);

        verify(customerRepository,times(1)).deleteById(any());
       verify(customerRepository,times(1)).findById(any());
    }

}
