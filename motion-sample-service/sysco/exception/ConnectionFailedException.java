package com.sysco.sampleService.Stas.exception;

public class ConnectionFailedException extends RuntimeException{
    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
