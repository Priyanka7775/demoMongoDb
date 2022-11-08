package com.example.demoMongoDb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {
    @Id
    private int custid;
    private String custname;
    private String email;
    private Address address;

    public Customer(){}
    public Customer(int custid, String custname, String email, Address address) {
        this.custid = custid;
        this.custname = custname;
        this.email = email;
        this.address = address;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custid=" + custid +
                ", custname='" + custname + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
