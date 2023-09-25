package com.example.userformbackend.controller;

import com.example.userformbackend.exception.BadReqException;
import com.example.userformbackend.model.Response;
import com.example.userformbackend.model.UserData;
import com.example.userformbackend.repository.UserRepository;
import com.example.userformbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(path = "/postuser")
    public ResponseEntity<Response> addUser(/*@Valid*/ @RequestBody UserData userData) throws BadReqException {
        log.info("Received Request :" + userData);
        /*Validate request - check if reqired parameters are present*/
        if(userData!=null){
            String message = "";
            if(userData.getFirstName() == null || userData.getFirstName().isEmpty()){
                message = message + "Missing First Name ";
            }
            if (userData.getLastName() == null || userData.getLastName().isEmpty()) {
                message = message + "Missing Last Name ";
            }
            if (userData.getGender() == null || userData.getGender().isEmpty()) {
                message = message + "Missing Gender ";
            }
            if (userData.getComment() == null || userData.getComment().isEmpty()) {
                message = message + "Missing Comment ";
            }

            if(!message.isEmpty()){
                throw new BadReqException(message);
            }
            /*Call Service layer for adding user details*/
            return userService.addUserToRepo(userData);
        }
        else{
            throw new BadReqException("All Fields are null");
        }
    }
}
