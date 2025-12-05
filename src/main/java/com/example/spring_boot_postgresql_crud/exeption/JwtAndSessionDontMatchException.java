package com.example.spring_boot_postgresql_crud.exeption;

public class JwtAndSessionDontMatchException extends RuntimeException {
    public JwtAndSessionDontMatchException(String message) {
        super(message);
    }
}
