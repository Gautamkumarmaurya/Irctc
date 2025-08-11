package com.subString.irctc.Exception;

public class ResponseNotFoundException extends RuntimeException {

    ResponseNotFoundException(){
        super("Resource not found !!");
    }
    public ResponseNotFoundException(String message){
        super(message);
    }
}
