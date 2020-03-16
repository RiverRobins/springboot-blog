package com.codeup.springblog.repositories;

import com.codeup.springblog.models.RateComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateComments extends JpaRepository<RateComment, Long> {
}
