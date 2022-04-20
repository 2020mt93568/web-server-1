package com.example.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByReferenceId(String referenceId);
    User findByEmailId(String email);

}
