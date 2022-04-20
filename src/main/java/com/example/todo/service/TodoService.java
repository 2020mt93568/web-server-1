package com.example.todo.service;

import org.springframework.stereotype.Component;

import com.example.todo.data.Todo;

import java.util.List;

@Component
public interface TodoService {

    Todo create(Todo obj);

    Todo read(String referenceId);

    List<Todo> findByProfileId(String profileId);

    Todo update(Todo obj);

}
