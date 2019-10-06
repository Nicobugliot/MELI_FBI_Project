package com.example.project.exception;


import com.example.project.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCurrencyException.class)
    public final ResponseEntity handleInvalidCurrencyException(InvalidCurrencyException ex, WebRequest request) {
        String details = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse("Invalid currency", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEventTypeException.class)
    public final ResponseEntity handleInvalidEventTypeException(InvalidEventTypeException ex, WebRequest request) {
        String details = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse("Invalid event type", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public final ResponseEntity handleInvalidAmountException(InvalidAmountException ex, WebRequest request) {
        String details = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse("Invalid amount", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
