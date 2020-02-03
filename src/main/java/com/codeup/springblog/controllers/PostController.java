package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    private final EmailSvc emailSvc;

    private final Doggo doggo = new Doggo();

    public PostController(Posts pDoa, Users usersDoa, Comments commentsDoa, EmailSvc emailService) {
        this.emailSvc = emailService;
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
    }

    @GetMapping(path = "/posts")
    public String posts(Model model) {
        model.addAttribute("posts", Doggo.reverse(pDoa.findAll()));
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String post(@PathVariable String id, Model model) {
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        return "posts/post-view";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String editGet(@PathVariable String id, Model model){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (cUser.getId() == (long) pDoa.findById(Long.parseLong(id)).get().getUser_id(cUser.getId())) {
            Post old = pDoa.getOne(Long.parseLong(id));
            model.addAttribute("post", old);
            return "posts/post-edit";
        }
        return "redirect:posts/" + id;
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String editPost(@PathVariable String id, Model model, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post old = pDoa.getOne(Long.parseLong(id));
        Post temp = new Post(old.getId(), title, body, old.getUser().getId());
        pDoa.save(temp);
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        return "posts/post-view";
    }

    @PostMapping(path = "/posts/{id}/delete")
    public String delete(@PathVariable String id){
        pDoa.deleteById(Long.parseLong(id));
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/create")
    public String createGet(Model model) {
        model.addAttribute("user", usersDoa.findById(5L));
        model.addAttribute("post", new Post());
        return "posts/post-create";
    }

    @PostMapping(path = "/posts/create")
    public String createPost(@ModelAttribute Post temp) { //@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @RequestParam(name = "user"), Long user
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        temp.setUser_id(cUser.getId());
        this.emailSvc.prepareAndSend(temp, "A new post was created!", temp.getTitle() + "\n" + temp.getBody());
        return "redirect:/posts";
    }
    @PostMapping(path = "/posts/{postId}/edit/delete-comment/{commentId}")
    public String delete(@PathVariable String postId, @PathVariable String commentId){
        commentsDoa.deleteById(Long.parseLong(commentId));
        return "redirect:/posts/" + postId + "/edit";
    }
}
