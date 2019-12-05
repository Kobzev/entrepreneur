package com.demo.entrepreneur.exception;

public class UnsupportedEmailException extends RuntimeException {
    public UnsupportedEmailException() {
        super();
    }

    public UnsupportedEmailException(String message) {
        super(message);
    }
}
