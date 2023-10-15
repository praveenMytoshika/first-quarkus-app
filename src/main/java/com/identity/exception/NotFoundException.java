package com.identity.exception;

public class NotFoundException extends PlatformException {
    public NotFoundException(String message) {
        super(message, 404);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, 404);
    }

    @Override
    public String getType() {
        return "com.identity/not-found";
    }
}
