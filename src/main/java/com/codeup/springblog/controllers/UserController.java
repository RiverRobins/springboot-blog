package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    public UserController(Posts pDoa, Users usersDoa, Comments commentsDoa) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
    }

    @GetMapping(path = "/users")
    public String users(Model model) {
        model.addAttribute("posts", usersDoa.findAll());
        return "users/user-all";
    }

    @GetMapping(path = "/users/{id}")
    public String post(@PathVariable String id, Model model) {
        model.addAttribute("user", usersDoa.getOne(Long.parseLong(id)));
        return "users/user-profile";
    }
}
