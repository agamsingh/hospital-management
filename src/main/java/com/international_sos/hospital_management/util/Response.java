package com.international_sos.hospital_management.util;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class Response<T> {

    private T data;
    private HttpStatus status;

    public Response(@Nullable T body, HttpStatus status){
        this.data = body;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public HttpStatus getStatus() {
        return status;
    }
}