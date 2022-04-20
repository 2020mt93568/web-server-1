package com.example.todo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.todo.data.Profile;
import com.example.todo.dto.request.LoginRequestDTO;
import com.example.todo.dto.response.LoginResponseDTO;
import com.example.todo.dto.request.RegisterRequestDTO;
import com.example.todo.exception.GenericException;
import com.example.todo.repo.ProfileRepository;
import com.example.todo.util.JWTUtil;
import com.example.todo.service.AuthService;

import java.util.Date;
import java.util.UUID;


@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO obj) {
        try {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(obj.getEmailId(), obj.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            String token = jwtUtil.generateToken(obj.getEmailId());

            return new LoginResponseDTO(token);
        } catch (AuthenticationException authenticationException) {
            throw new GenericException("Invalid Login Credentials", HttpStatus.UNAUTHORIZED);
        }
    };

    @Override
    public Profile register(RegisterRequestDTO obj) {
        Profile profile = new Profile();
//        profile.setId(null);
        profile.setEmailId(obj.getEmailId());
        profile.setUsername(obj.getUsername());
        profile.setPassword(obj.getPassword());
        profile.setReferenceId(UUID.randomUUID().toString());
        profile.setCreatedAt(new Date());
        profile.setUpdatedAt(new Date());
        return profileRepository.save(profile);
    };
}
