package com.example.todo.service;

import org.springframework.stereotype.Component;

import com.example.todo.data.User;

import com.example.todo.dto.request.LoginRequestDTO;
import com.example.todo.dto.response.LoginResponseDTO;
import com.example.todo.dto.request.RegisterRequestDTO;

@Component
public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO obj);
    User register(RegisterRequestDTO obj);

}