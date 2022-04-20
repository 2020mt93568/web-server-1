package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.example.todo.dto.request.ProfileUpdateRequestDTO;
import com.example.todo.impl.ProfileServiceImpl;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @GetMapping({"/{referenceId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> read(@PathVariable(value = "referenceId") String referenceId) {
        return ResponseEntity.ok(profileService.read(referenceId));
    }


    @GetMapping({"/emailId/{emailId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProfileByEmail(@PathVariable(value = "emailId") String emailId) {
        return ResponseEntity.ok(profileService.findByEmailId(emailId));
    }

    @PatchMapping({"/{referenceId}"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@PathVariable("referenceId") String referenceId,@Valid @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO) {
        return ResponseEntity.ok(profileService.update(referenceId, profileUpdateRequestDTO));
    }

}
