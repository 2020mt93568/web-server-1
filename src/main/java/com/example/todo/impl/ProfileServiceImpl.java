package com.example.todo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.todo.data.Profile;
import com.example.todo.dto.response.ProfileResponseDTO;
import com.example.todo.dto.request.ProfileUpdateRequestDTO;
import com.example.todo.exception.GenericException;
import com.example.todo.repo.ProfileRepository;
import com.example.todo.service.ProfileService;

import java.util.Date;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public ProfileResponseDTO read(String referenceId) {
        return objectMapper.convertValue(profileRepository.findByReferenceId(referenceId), ProfileResponseDTO.class);
    };

    @Override
    public Profile findByEmailId(String emailId) {
        Profile profileByEmailId = profileRepository.findByEmailId(emailId);
        if(profileByEmailId == null) {
            throw new GenericException("No Profile with this email found", HttpStatus.NOT_FOUND);
        } else {
            return profileByEmailId;
        }
    };

    @Override
    public ProfileResponseDTO update(String referenceId, ProfileUpdateRequestDTO updateObj) {
        Profile profile = profileRepository.findByReferenceId(referenceId);
        if (profile == null) {
            throw new GenericException("No profile exists", HttpStatus.NOT_FOUND);
        }
        boolean hasUpdated = false;
        if (updateObj.getUsername() != null) {
            hasUpdated = true;
            profile.setUsername(updateObj.getUsername());
        }

        if (updateObj.getPassword() != null) {
            hasUpdated = true;
            if (updateObj.getPassword() != profile.getPassword()) {
                throw new GenericException("Incorrect Password", HttpStatus.UNAUTHORIZED);
            }
            if (updateObj.getNewPassword() == null) {
                throw new GenericException("New Password cannot be blank", HttpStatus.BAD_REQUEST);
            }
            profile.setPassword(updateObj.getNewPassword());
        }
        if (!hasUpdated) {
            throw new GenericException("Nothing to update", HttpStatus.BAD_REQUEST);
        }

        profile.setUpdatedAt(new Date());
        profileRepository.save(profile);
        ProfileResponseDTO profileResponseObj = objectMapper.convertValue(profile, ProfileResponseDTO.class);
        return profileResponseObj;
    };


}
