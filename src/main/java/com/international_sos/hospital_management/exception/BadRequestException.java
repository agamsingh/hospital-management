package com.international_sos.hospital_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private String message;
    private Map<String, String> metaData;

    public BadRequestException(String message, Map<String, String> metaData) {
        super(message);
        this.message = message;
        this.metaData = metaData;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }
}
