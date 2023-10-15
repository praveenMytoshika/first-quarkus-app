package com.identity.exception;

public class BadRequestException extends PlatformException {
    public BadRequestException(String message) {
        super(message, 400);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, 400);
    }

    @Override
    public String getType() {
        return "com.identity/bad-request";
    }
}
