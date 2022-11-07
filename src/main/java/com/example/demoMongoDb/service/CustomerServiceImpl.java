package com.example.demoMongoDb.service;

import com.example.demoMongoDb.domain.Customer;
import com.example.demoMongoDb.exception.CustomerNotFoundException;
import com.example.demoMongoDb.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerData() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int custid) throws CustomerNotFoundException {
        boolean result=false;
        if(customerRepository.findById(custid).isEmpty()){
            throw new CustomerNotFoundException();
        }else {
            customerRepository.deleteById(custid);
            result=true;

        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomerByCity(String city) throws CustomerNotFoundException {
        if(customerRepository.findAllCustomerFromCity(city).isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customerRepository.findAllCustomerFromCity(city);
    }
}
