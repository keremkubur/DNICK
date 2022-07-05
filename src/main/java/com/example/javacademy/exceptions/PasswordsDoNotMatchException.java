package com.example.javacademy.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords Do Not Match Exception!");
    }
}
