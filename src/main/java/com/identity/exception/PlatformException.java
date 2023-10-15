package com.identity.exception;

public abstract class PlatformException extends RuntimeException {

    private final int statusCode;

    protected PlatformException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    protected PlatformException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public abstract String getType();
}
