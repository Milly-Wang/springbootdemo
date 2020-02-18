package com.milly.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFound extends RuntimeException {
    public BookNotFound() {
    }

    public BookNotFound(String message) {
        super(message);
    }

    public BookNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
