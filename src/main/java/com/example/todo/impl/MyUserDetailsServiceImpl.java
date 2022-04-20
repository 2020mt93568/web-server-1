package com.example.todo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.todo.data.Profile;

import java.util.Collections;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired private ProfileServiceImpl profileService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile profile = profileService.findByEmailId(email);
        return new org.springframework.security.core.userdetails.User(
                email,
                profile.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}