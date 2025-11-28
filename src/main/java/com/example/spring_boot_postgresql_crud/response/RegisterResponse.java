package com.example.spring_boot_postgresql_crud.response;

import com.example.spring_boot_postgresql_crud.model.User;
import jakarta.servlet.http.HttpSession;

public class RegisterResponse {
    private HttpSession session;

    private User user;

    public RegisterResponse(HttpSession session, User user) {
        this.session = session;
        this.user = user;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public RegisterResponse setUser(User user) {
        this.user = user;
        return this;
    }
}
