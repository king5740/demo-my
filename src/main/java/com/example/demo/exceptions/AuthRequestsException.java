package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class AuthRequestsException extends RuntimeException {

    public AuthRequestsException(String message) {
        super(message);
    }

    public AuthRequestsException(String message, Throwable cause) {
        super(message, cause);
    }
}
