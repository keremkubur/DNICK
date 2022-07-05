package com.example.javacademy.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid User Credentials exception");
    }
}
