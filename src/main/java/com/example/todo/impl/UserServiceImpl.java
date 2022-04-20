package com.example.todo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.todo.data.User;
import com.example.todo.dto.response.UserResponseDTO;
import com.example.todo.dto.request.UserUpdateRequestDTO;
import com.example.todo.exception.GenericException;
import com.example.todo.repo.UserRepository;
import com.example.todo.service.UserService;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public UserResponseDTO read(String referenceId) {
        return objectMapper.convertValue(userRepository.findByReferenceId(referenceId), UserResponseDTO.class);
    };

    @Override
    public User findByEmailId(String emailId) {
        User userByEmailId = userRepository.findByEmailId(emailId);
        if(userByEmailId == null) {
            throw new GenericException("No User with this email found", HttpStatus.NOT_FOUND);
        } else {
            return userByEmailId;
        }
    };

    @Override
    public UserResponseDTO update(String referenceId, UserUpdateRequestDTO updateObj) {
        User user = userRepository.findByReferenceId(referenceId);
        if (user == null) {
            throw new GenericException("No user exists", HttpStatus.NOT_FOUND);
        }
        boolean hasUpdated = false;
        if (updateObj.getUsername() != null) {
            hasUpdated = true;
            user.setUsername(updateObj.getUsername());
        }

        if (updateObj.getPassword() != null) {
            hasUpdated = true;
            if (updateObj.getPassword() != user.getPassword()) {
                throw new GenericException("Incorrect Password", HttpStatus.UNAUTHORIZED);
            }
            if (updateObj.getNewPassword() == null) {
                throw new GenericException("New Password cannot be blank", HttpStatus.BAD_REQUEST);
            }
            user.setPassword(updateObj.getNewPassword());
        }
        if (!hasUpdated) {
            throw new GenericException("Nothing to update", HttpStatus.BAD_REQUEST);
        }

        user.setUpdatedAt(new Date());
        userRepository.save(user);
        UserResponseDTO userResponseObj = objectMapper.convertValue(user, UserResponseDTO.class);
        return userResponseObj;
    };


}
