package com.example.demoMongoDb.controller;

import com.example.demoMongoDb.domain.Customer;
import com.example.demoMongoDb.exception.CustomerNotFoundException;
import com.example.demoMongoDb.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custdata/api/")
public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/cust")
    public ResponseEntity<?> insertCustomer(@RequestBody Customer customer){
        Customer customer1=customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);

    }

    @GetMapping("/cust1")
    public ResponseEntity<?> fetchAllCustomer() throws Exception {
        return new ResponseEntity<>(customerService.getAllCustomerData(),HttpStatus.OK);
    }

    @DeleteMapping("customer/{custid}")
    public ResponseEntity<?> deleteSingleCustomer(@PathVariable("custid") int custid) throws CustomerNotFoundException{
      //  return new ResponseEntity<>(customerService.deleteCustomer(custid),HttpStatus.OK);
        ResponseEntity responseEntity=null;
        try{
            customerService.deleteCustomer(custid);
            responseEntity=new ResponseEntity("Successfully Deleted",HttpStatus.OK);

        }catch (CustomerNotFoundException cnfe){
            throw new CustomerNotFoundException();
        }catch (Exception exception){
            responseEntity=new ResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("customer/{city}")
    public ResponseEntity<?> fetchByCity(@PathVariable String city){
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity(customerService.getAllCustomerByCity(city),HttpStatus.FOUND);

        }catch (CustomerNotFoundException e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
