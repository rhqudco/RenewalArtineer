package com.artineer.artineer.exception;

public class UserNotMatchedException extends Exception{
    public UserNotMatchedException() {
    }

    public UserNotMatchedException(String message) {
        super(message);
    }
}