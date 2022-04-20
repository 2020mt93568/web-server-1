package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.example.todo.dto.request.UserUpdateRequestDTO;
import com.example.todo.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping({"/{referenceId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> read(@PathVariable(value = "referenceId") String referenceId) {
        return ResponseEntity.ok(userService.read(referenceId));
    }


    @GetMapping({"/emailId/{emailId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserByEmail(@PathVariable(value = "emailId") String emailId) {
        return ResponseEntity.ok(userService.findByEmailId(emailId));
    }

    @PatchMapping({"/{referenceId}"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@PathVariable("referenceId") String referenceId,@Valid @RequestBody UserUpdateRequestDTO userUpdateObj) {
        return ResponseEntity.ok(userService.update(referenceId, userUpdateObj));
    }

}
