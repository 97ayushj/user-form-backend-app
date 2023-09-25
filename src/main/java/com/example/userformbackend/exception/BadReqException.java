package com.example.userformbackend.exception;

public class BadReqException extends Exception{

    public BadReqException(String message){
        super(message);
    }
    public BadReqException(){
        super();
    }

}
