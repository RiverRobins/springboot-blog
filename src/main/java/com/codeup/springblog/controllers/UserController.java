package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    private final PasswordEncoder pe;

    public UserController(Posts pDoa, Users usersDoa, Comments commentsDoa, PasswordEncoder pe) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
        this.pe = pe;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/signup";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = pe.encode(user.getPassword());
        user.setPassword(hash);
        usersDoa.save(user);
        return "redirect:/login";
    }

    @GetMapping(path = "/login")
    public String showLogin(){
        return "users/login";
    }

//    @PostMapping(path = "login")
//    public String login(){
//
//        return "redirect:/posts";
//    }

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
