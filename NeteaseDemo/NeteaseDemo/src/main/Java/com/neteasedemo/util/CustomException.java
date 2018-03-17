package com.neteasedemo.util;

public class CustomException {

    public static class EmptyUserException extends Exception {
        public EmptyUserException (String message) {
            super(message);
        }
    }

    public static class ItemAlreadyExistedException extends Exception {
        public ItemAlreadyExistedException (String message) {
            super(message);
        }
    }

    public static class ItemNotExistException extends Exception {
        public ItemNotExistException (String message) {
            super(message);
        }
    }

    public static class UpdateItemException extends Exception {
        public UpdateItemException (String message) {
            super(message);
        }
    }

    public static class ItemFullException extends Exception {
        public ItemFullException (String message) {
            super(message);
        }
    }

    public static class BuyItemException extends Exception {
        public BuyItemException (String message) {
            super(message);
        }
    }
}
