package com.example.spring_boot_postgresql_crud.exeption;

public class ExpiredSessionException extends RuntimeException {
    public ExpiredSessionException(String message) {
        super(message);
    }
}
