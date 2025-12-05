package com.example.spring_boot_postgresql_crud.controller;

import com.example.spring_boot_postgresql_crud.model.User;
import com.example.spring_boot_postgresql_crud.model.LoginUserDto;
import com.example.spring_boot_postgresql_crud.model.RegisterUserDto;
import com.example.spring_boot_postgresql_crud.response.RegisterResponse;
import com.example.spring_boot_postgresql_crud.service.AuthenticationService;
import com.example.spring_boot_postgresql_crud.service.JwtService;
import com.example.spring_boot_postgresql_crud.response.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

        RegisterResponse registerResponse = new RegisterResponse(session, registeredUser);

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto,  HttpSession session) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        session.setAttribute("user", authenticatedUser);
        session.setAttribute("jwtToken", jwtToken);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).setSession(session);

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {

        session.invalidate();

        return new ResponseEntity<>("session destroyed", HttpStatus.OK);
    }
}
