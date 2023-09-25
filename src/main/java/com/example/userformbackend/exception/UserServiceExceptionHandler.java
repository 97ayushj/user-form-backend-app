package com.example.userformbackend.exception;

import com.example.userformbackend.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadReqException.class)
    public ResponseEntity<Response> handleBadReqException(Exception e){
        Response response = new Response();
        response.setStatus("Failed");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
