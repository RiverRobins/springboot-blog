package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.RatePosts;
import com.codeup.springblog.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    private final EmailSvc emailSvc;

    private final RatePosts ratesDoa;

    private final Doggo doggo = new Doggo();

    public PostController(Posts pDoa, Users usersDoa, Comments commentsDoa, EmailSvc emailSvc, RatePosts ratesDoa) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
        this.emailSvc = emailSvc;
        this.ratesDoa = ratesDoa;
    }

    @GetMapping(path = "/")
    public String index(Model model) {
        List<Post> posts = Doggo.reverse(pDoa.findAll());
        model.addAttribute("posts", posts);
        return "posts/feed";
    }

    @GetMapping(path = "/posts")
    public String posts(Model model) {
        model.addAttribute("posts", Doggo.reverse(pDoa.findAll()));
        return "posts/feed";
    }

    @GetMapping(path = "/posts/{id}")
    public String post(@PathVariable String id, Model model) {
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        return "posts/post-view";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String editGet(@PathVariable String id, Model model){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (cUser.getId() == (long) pDoa.findById(Long.parseLong(id)).get().getUser().getId()) {
            Post old = pDoa.getOne(Long.parseLong(id));
            model.addAttribute("post", old);
            return "posts/post-edit";
        }
        return "redirect:posts/" + id;
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String editPost(@PathVariable String id, Model model, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post old = pDoa.getOne(Long.parseLong(id));
        Post temp = new Post(old.getId(), title, body, old.getUser());
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
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", usersDoa.findById(cUser.getId()));
        model.addAttribute("post", new Post());
        return "posts/post-create";
    }

    @PostMapping(path = "/posts/create")
    public String createPost(@ModelAttribute Post temp) {
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        temp.setUser_id(cUser.getId());
        pDoa.save(temp);
//        this.emailSvc.prepareAndSend(temp, "A new post was created!", temp.getTitle() + "\n" + temp.getBody());
        return "redirect:/posts";
    }

    @PostMapping(path = "/posts/{postId}/edit/delete-comment/{commentId}")
    public String deleteCom(@PathVariable String postId, @PathVariable String commentId){
        commentsDoa.deleteById(Long.parseLong(commentId));
        return "redirect:/posts/" + postId + "/edit";
    }

//    @PostMapping(path = "/post/{id}/like")
//    public String likeD(@PathVariable(name = "id") String id){
//        String page = "redirect:/posts/" + id;
//        try {
//            switch (rate.getFrom()){
//                case "view":
//                    page = "redirect:/posts/" + rate.getId();
//                    break;
//                case "feed":
//                    page = "redirect:/posts";
//                    break;
//            }
//        } catch (Exception e){
//
//        }
//        return page;
//    }

    @RequestMapping(value = "/posts/{id}/like", method = RequestMethod.POST)
    public void like(@PathVariable(name = "id") String postId, @RequestBody RateAjax rate /*@RequestParam(name = "post") String id, @RequestParam(name = "from") String from*/){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RatePost temp = new RatePost((byte) 1, cUser.getId(), rate.getId());
        ratesDoa.save(temp);

    }
}
