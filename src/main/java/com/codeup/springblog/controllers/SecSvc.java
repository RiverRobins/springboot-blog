package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserWR;
import com.codeup.springblog.repositories.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecSvc implements UserDetailsService {
    private final Users users;

    public SecSvc(Users users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWR(user);
    }
}