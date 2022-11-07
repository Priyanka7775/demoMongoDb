package com.example.demoMongoDb.service;

import com.example.demoMongoDb.domain.Customer;
import com.example.demoMongoDb.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomerData() throws Exception ;
    boolean deleteCustomer(int custid) throws CustomerNotFoundException;
    List<Customer> getAllCustomerByCity(String city) throws CustomerNotFoundException;
}
