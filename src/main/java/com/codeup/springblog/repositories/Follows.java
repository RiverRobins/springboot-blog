package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Follows extends JpaRepository<Follow, Long>  {

}
