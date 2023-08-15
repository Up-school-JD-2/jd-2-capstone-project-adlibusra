package io.upschool.exception;

public class StatusAlreadySavedException extends RuntimeException{
    public StatusAlreadySavedException(String message) {
        super(message);
    }
}
