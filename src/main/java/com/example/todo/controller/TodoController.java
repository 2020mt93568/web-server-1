package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.example.todo.data.Todo;
import com.example.todo.impl.TodoServiceImpl;

@RestController
@RequestMapping("/api/v1/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Todo obj) {
        return ResponseEntity.ok(todoService.create(obj));
    }


    @GetMapping({"/{referenceId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> read(@PathVariable(value = "referenceId") String referenceId) {
        return ResponseEntity.ok(todoService.read(referenceId));
    }

    @GetMapping({"/profileId/{profileId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findByProfileId(
            @PathVariable(value = "profileId") String profileId
    ) {
        return ResponseEntity.ok(todoService.findByProfileId(profileId));
    }

    @PutMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Todo obj) {
        return ResponseEntity.ok(todoService.update(obj));
    }

}
