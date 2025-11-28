package com.example.spring_boot_postgresql_crud.controller;

import com.example.spring_boot_postgresql_crud.model.User;
import com.example.spring_boot_postgresql_crud.model.LoginUserDto;
import com.example.spring_boot_postgresql_crud.model.RegisterUserDto;
import com.example.spring_boot_postgresql_crud.response.RegisterResponse;
import com.example.spring_boot_postgresql_crud.service.AuthenticationService;
import com.example.spring_boot_postgresql_crud.service.JwtService;
import com.example.spring_boot_postgresql_crud.response.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterUserDto registerUserDto, HttpSession session) {
        User registeredUser = authenticationService.signup(registerUserDto);
        session.setAttribute("user", registeredUser);

        RegisterResponse registerResponse = new RegisterResponse(session, registeredUser);

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto,  HttpSession session) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        session.setAttribute("user", authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).setSession(session);

        return ResponseEntity.ok(loginResponse);
    }
}
