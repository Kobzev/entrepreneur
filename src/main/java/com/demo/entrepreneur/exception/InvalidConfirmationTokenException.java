package com.demo.entrepreneur.exception;

public class InvalidConfirmationTokenException extends RuntimeException{

    public InvalidConfirmationTokenException() {
        super();
    }

    public InvalidConfirmationTokenException(String message) {
        super(message);
    }

    public InvalidConfirmationTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
