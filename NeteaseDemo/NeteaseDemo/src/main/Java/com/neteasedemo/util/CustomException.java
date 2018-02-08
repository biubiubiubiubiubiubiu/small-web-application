package com.neteasedemo.util;

public class CustomException {

    public static class EmptyUserException extends Exception {
        public EmptyUserException (String message) {
            super(message);
        }
    }
}
