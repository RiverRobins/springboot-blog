package com.codeup.springblog.repositories;

import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Follows extends JpaRepository<User, Long>  {

}
