package com.example.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.data.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByReferenceId(String referenceId);
    Profile findByEmailId(String email);

}
