package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
    @ResponseBody
    public String posts() {
        return "posts";
    }

    @GetMapping(path = "/posts/{id}")
    @ResponseBody
    public String spec(@PathVariable String id) {
        return "post(singular) with ID of " + id;
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createGet() {
        return "Create post form";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost() {
        return "Create post add";
    }
}
