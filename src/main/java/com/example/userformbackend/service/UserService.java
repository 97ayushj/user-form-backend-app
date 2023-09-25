package com.example.userformbackend.service;


import com.example.userformbackend.model.Response;
import com.example.userformbackend.model.UserData;
import com.example.userformbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Response> addUserToRepo(UserData userData) {
        Response response = new Response();
        UserData userCreated = null;
        try {
            List<UserData> userDataList = userRepository.checkExistingUser(userData.getFirstName(), userData.getLastName());

            /*Check If the user is already existing or not - if existing then return conflict response*/
            if (!userDataList.isEmpty()) {
                response.setMessage("User already exists with First Name : " + userData.getFirstName() + " Last Name : " + userData.getLastName());
                response.setStatus("Failed");
                log.debug("User already exists with First Name : " + userData.getFirstName() + " Last Name : " + userData.getLastName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            /*If user is not already existing then save user to database*/
            userCreated = userRepository.save(userData);
            response.setStatus("Success");
            response.setMessage("User Successfully Created First Name : " + userCreated.getFirstName() + " Last Name : " + userCreated.getLastName());
            log.debug("User Successfully Created First Name : " + userCreated.getFirstName() + " Last Name : " + userCreated.getLastName());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus("Failed");
            response.setMessage(e.getLocalizedMessage());
            log.debug("Exception Occured : " + e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
