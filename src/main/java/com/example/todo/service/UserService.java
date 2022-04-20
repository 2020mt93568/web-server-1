package com.example.todo.service;


import org.springframework.stereotype.Component;

import com.example.todo.data.User;

import com.example.todo.dto.response.UserResponseDTO;
import com.example.todo.dto.request.UserUpdateRequestDTO;

@Component
public interface UserService {

    UserResponseDTO read(String referenceId);

    User findByEmailId(String emailId);

    UserResponseDTO update(String referenceId, UserUpdateRequestDTO updateObj);

}
