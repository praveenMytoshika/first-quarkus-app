package com.identity.exception;

public class ServerException extends PlatformException {
    public ServerException(String message) {
        super(message, 500);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause, 500);
    }

    @Override
    public String getType() {
        return "com.identity/server-error";
    }
}
