package com.library.utils;

public class BorrowerNotFoundException extends NullPointerException{
    public BorrowerNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
