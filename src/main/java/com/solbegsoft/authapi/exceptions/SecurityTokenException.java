package com.solbegsoft.authapi.exceptions;


/**
 * Security Exception
 */
public class SecurityTokenException extends RuntimeException{

    public SecurityTokenException() {
        super();
    }

    public SecurityTokenException(String message) {
        super(message);
    }

    public SecurityTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityTokenException(Throwable cause) {
        super(cause);
    }
}
