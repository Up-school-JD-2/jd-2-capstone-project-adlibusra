package io.upschool.exception;

public class RouteAlredySaved extends RuntimeException{
    public RouteAlredySaved(String message) {
        super(message);
    }
}
