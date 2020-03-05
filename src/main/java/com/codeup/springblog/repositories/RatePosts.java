package com.codeup.springblog.repositories;

import com.codeup.springblog.models.RatePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePosts extends JpaRepository<RatePost, Long> {
}
