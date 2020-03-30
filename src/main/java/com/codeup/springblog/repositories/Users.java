package com.codeup.springblog.repositories;

import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Users extends JpaRepository<User, Long>  {
    User findByUsername(String username);
}
