package com.bridgelabz.bookstore.util;

public class ErrorResponse extends Response {

    public ErrorResponse(Integer statusCode, String statusMessage, Object token) {
        super(statusCode, statusMessage, token);
    }

}
