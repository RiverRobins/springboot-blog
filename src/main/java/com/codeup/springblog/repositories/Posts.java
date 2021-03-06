package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Posts extends JpaRepository<Post, Long> {
    void deleteById(Long n);
}
