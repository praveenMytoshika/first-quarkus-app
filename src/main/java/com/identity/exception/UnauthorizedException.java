package com.identity.exception;

public class UnauthorizedException extends PlatformException {

    public UnauthorizedException(String message) {
        super(message, 401);
    }

    @Override
    public String getType() {
        return "com.identity/unauthorized";
    }
}
