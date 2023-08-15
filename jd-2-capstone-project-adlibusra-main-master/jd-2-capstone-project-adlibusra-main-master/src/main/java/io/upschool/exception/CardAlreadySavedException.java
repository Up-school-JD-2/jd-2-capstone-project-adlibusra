package io.upschool.exception;

public class CardAlreadySavedException extends  RuntimeException{
    public CardAlreadySavedException(String message) {
        super(message);
    }
}

