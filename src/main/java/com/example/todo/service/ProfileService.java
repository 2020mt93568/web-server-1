package com.example.todo.service;


import org.springframework.stereotype.Component;

import com.example.todo.data.Profile;

import com.example.todo.dto.response.ProfileResponseDTO;
import com.example.todo.dto.request.ProfileUpdateRequestDTO;

@Component
public interface ProfileService {

    ProfileResponseDTO read(String referenceId);

    Profile findByEmailId(String emailId);

    ProfileResponseDTO update(String referenceId, ProfileUpdateRequestDTO updateObj);

}
