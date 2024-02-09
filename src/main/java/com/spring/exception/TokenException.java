package com.spring.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends Exception {
    private String status_code;
    private HttpStatus status;

    public TokenException(String message, String status_code, HttpStatus status) {
        super(message);
        this.status_code = status_code;
        this.status = status;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
