package com.udemy.com.udemy.controller;

import com.udemy.com.udemy.model.ContactModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @GetMapping("/checkRest")
    public ResponseEntity<ContactModel> checkRest(){
        ContactModel contactModel = new ContactModel(2, "Paul", "Miranda", "157347", "Mexicali");
        return new ResponseEntity<ContactModel>(contactModel, HttpStatus.OK);
    }
}
