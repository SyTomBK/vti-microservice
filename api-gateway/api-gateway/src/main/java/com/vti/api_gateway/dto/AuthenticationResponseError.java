package com.vti.api_gateway.dto;

public class AuthenticationResponseError {

    private final int status;
    private final String message;
    public AuthenticationResponseError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
