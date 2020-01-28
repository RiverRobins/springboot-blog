package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.Post;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
//    @ResponseBody
    public String posts(Model model) {
        ArrayList<Post> temp = new ArrayList<>();
        temp.add(new Post(1L,1L,"title","body text", new ArrayList<Comment>()));
        temp.add(new Post(1L,1L,"title 2","body text 2", new ArrayList<Comment>()));
        temp.add(new Post(1L,1L,"title 3","body text 3", new ArrayList<Comment>()));
        temp.add(new Post(1L,1L,"title 4","body text 4", new ArrayList<Comment>()));
        model.addAttribute("posts", temp);
        return "posts/index";
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
