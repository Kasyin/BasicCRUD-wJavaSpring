package com.example.spring_boot_postgresql_crud.response;

import jakarta.servlet.http.HttpSession;

public class LoginResponse {
    private HttpSession session;

    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public HttpSession getSession() {
        return session;
    }

    public LoginResponse setSession(HttpSession session) {
        this.session = session;
        return this;
    }
}
