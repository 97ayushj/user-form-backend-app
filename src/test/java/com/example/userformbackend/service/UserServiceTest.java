package com.example.userformbackend.service;

import com.example.userformbackend.model.Response;
import com.example.userformbackend.model.UserData;
import com.example.userformbackend.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Non Existing User")
    void addUserToRepo() {
        List<UserData> EmptyUserDataList = new ArrayList<>();
        UserData userData = new UserData();
        userData.setUserId(1);
        userData.setFirstName("Ayush");
        userData.setLastName("Jain");
        userData.setGender("Male");
        userData.setComment("Hey");


        Mockito.when(userRepository.checkExistingUser(userData.getFirstName(), userData.getLastName())).thenReturn(EmptyUserDataList);
        Mockito.when(userRepository.save(userData)).thenReturn(userData);

        //Expected Response
        Response response = new Response();
        response.setStatus("Success");
        response.setMessage("User Successfully Created First Name : Ayush Last Name : Jain");


        Assertions.assertEquals(new ResponseEntity(response, HttpStatus.OK), userService.addUserToRepo(userData));


    }

    @Test
    @DisplayName("Existing User")
    void addUserToRepoExistingUser() {
        List<UserData> UserDataList = new ArrayList<>();
        UserData userData = new UserData();
        userData.setUserId(1);
        userData.setFirstName("Ayush");
        userData.setLastName("Jain");
        userData.setGender("Male");
        userData.setComment("Hey");
        UserDataList.add(userData);


        Mockito.when(userRepository.checkExistingUser(userData.getFirstName(), userData.getLastName())).thenReturn(UserDataList);
        Mockito.when(userRepository.save(userData)).thenReturn(userData);


        //Expected Response
        Response response = new Response();
        response.setStatus("Failed");
        response.setMessage("User already exists with First Name : Ayush Last Name : Jain");


        Assertions.assertEquals(new ResponseEntity(response, HttpStatus.OK), userService.addUserToRepo(userData));


    }

    @Test
    @DisplayName("Internal Server Scenario")
    void addUserToRepoException() {
        List<UserData> UserDataList = new ArrayList<>();
        UserData userData = new UserData();
        userData.setUserId(1);
        userData.setFirstName("Ayush");
        userData.setLastName("Jain");
        userData.setGender("Male");
        userData.setComment("Hey");
        UserDataList.add(userData);


        Mockito.when(userRepository.checkExistingUser(userData.getFirstName(), userData.getLastName())).thenThrow(new RuntimeException("Uknown Error"));


        //Expected Response
        Response response = new Response();
        response.setStatus("Failed");
        response.setMessage("Uknown Error");


        Assertions.assertEquals(new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR), userService.addUserToRepo(userData));


    }
}