package com.international_sos.hospital_management.exception;

public class AlreadyExistsException extends RuntimeException{
    private String message;

    public AlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
