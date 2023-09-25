package com.example.userformbackend.controller;

import com.example.userformbackend.exception.BadReqException;
import com.example.userformbackend.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Invalid Request")
    void addUserTestInvalidReq() throws Exception {

        UserData userData = new UserData();
        userData.setFirstName("");
        userData.setLastName("Jain");
        userData.setGender("Male");
        userData.setComment("Hey");

        mockMvc.perform(post("/postuser").content(asJsonReq(userData)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(result -> {
            Assertions.assertTrue(result.getResolvedException() instanceof BadReqException);
        });
    }


    @Test
    @DisplayName("Valid Request")
    void addUserTestValidReq() throws Exception {

        UserData userData = new UserData();
        userData.setFirstName("Ayush");
        userData.setLastName("Jain");
        userData.setGender("Male");
        userData.setComment("Hey");

 /*       Response response = new Response();
        response.setStatus("Success");
        response.setMessage("");
        ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, HttpStatus.IM_USED);

        Mockito.when(userService.addUserToRepo(userData)).thenReturn(responseEntity);*/

        mockMvc.perform(post("/postuser").content(asJsonReq(userData)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json("{\n" +
                "    \"status\": \"Success\",\n" +
                "    \"message\": \"User Successfully Created First Name : Ayush Last Name : Jain\"\n" +
                "}"));
    }


    private String asJsonReq(Object obj) {
        String str = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            str = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {

        }
        return str;
    }
}