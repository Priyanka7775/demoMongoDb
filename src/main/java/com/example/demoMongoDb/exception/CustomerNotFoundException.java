package com.example.demoMongoDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "customer id Not Found which is given by you")
public class CustomerNotFoundException extends Exception{
//    public CustomerNotFoundException(){
//        super();
//    }
}
