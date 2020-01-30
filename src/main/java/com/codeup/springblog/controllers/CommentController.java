package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    public CommentController(Posts pDoa, Users usersDoa, Comments commentsDoa) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
    }

//    @GetMapping(path = "/posts/{id}/edit")
//    public String editGet(@PathVariable String id, Model model){
//        Post old = pDoa.getOne(Long.parseLong(id));
//        model.addAttribute("post", old);
//        return "posts/post-edit";
//    }

    @PostMapping(path = "/posts/{id}/comment")
    public String createPost(Model model, @PathVariable(name = "id") String postId, @RequestParam(name = "body") String body, @RequestParam(name = "user") String user) {
        Comment temp = new Comment(body, usersDoa.getOne(Long.parseLong(user)), pDoa.getOne(Long.parseLong(postId)));
        commentsDoa.save(temp);
        return "redirect:/posts/" + postId;
    }
}
