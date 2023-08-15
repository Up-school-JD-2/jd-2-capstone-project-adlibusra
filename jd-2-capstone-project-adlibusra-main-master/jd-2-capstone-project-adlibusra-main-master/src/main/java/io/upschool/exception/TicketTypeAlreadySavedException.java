package io.upschool.exception;

public class TicketTypeAlreadySavedException extends RuntimeException {
    public TicketTypeAlreadySavedException(String message) {
        super(message);
    }
}
