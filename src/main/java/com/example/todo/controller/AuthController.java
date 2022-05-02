package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.dto.request.LoginRequestDTO;
import com.example.todo.dto.request.RegisterRequestDTO;
import com.example.todo.impl.AuthServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO userCred){
        return ResponseEntity.ok(authService.login(userCred));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO obj){
        return ResponseEntity.ok(authService.register(obj));
    }

}
