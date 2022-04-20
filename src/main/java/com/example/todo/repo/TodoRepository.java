package com.example.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.data.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    Todo findByReferenceId(String referenceId);
    List<Todo> findByUserId(String userId);

}
