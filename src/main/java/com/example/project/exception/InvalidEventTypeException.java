package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidEventTypeException extends RuntimeException {
    public InvalidEventTypeException(String exception) {
        super(exception);
    }
}
