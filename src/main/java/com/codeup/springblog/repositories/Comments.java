package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Comments extends JpaRepository<Comment, Long>  {
    List<Comments> findByPost_id(Long id);
    List<Comments> findByUser_id(Long id);
}
