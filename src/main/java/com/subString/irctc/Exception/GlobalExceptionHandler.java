package com.subString.irctc.Exception;

import com.subString.irctc.DTO.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// @ControllerAdvice  -> Basically used to return the HTML page and use The thymeleaf
@RestControllerAdvice
public class GlobalExceptionHandler {

    //pure project ke liye hai
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<errorResponse> NoSuchElementException(NoSuchElementException exception){
        errorResponse errorResponse = new errorResponse("Train Not Found !! "+exception.getMessage(),"404",false);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<errorResponse> ResponseNotFoundException(ResponseNotFoundException exception){
        errorResponse errorResponse = new errorResponse(exception.getMessage(),"404",false);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String , String>  errorResponse = new HashMap<String , String>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            String field = ((FieldError)error).getField();
            errorResponse.put(field,errorMessage);
        });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<errorResponse> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        String message = exception.getMessage().contains("Duplicate entry")?" You are trying to provides fields that are already database ":exception.getMessage();
        errorResponse errorResponse = new errorResponse(message,"400",false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



}
