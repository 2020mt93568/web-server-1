package com.example.todo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.data.Todo;
import com.example.todo.repo.TodoRepository;
import com.example.todo.service.TodoService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public Todo create(Todo obj) {
//        obj.setId(null);
        obj.setReferenceId(UUID.randomUUID().toString());
        obj.setCreatedAt(new Date());
        obj.setUpdatedAt(new Date());
        return todoRepository.save(obj);
    };

    @Override
    public Todo read(String referenceId) {
        return todoRepository.findByReferenceId(referenceId);
    };

    @Override
    public List<Todo> findByProfileId(String profileId) {
        return todoRepository.findByProfile(profileId);
    };

    @Override
    public Todo update(Todo obj) {
        obj.setUpdatedAt(new Date());
        return todoRepository.save(obj);
    };

}
