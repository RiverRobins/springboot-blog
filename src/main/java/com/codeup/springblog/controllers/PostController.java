package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    private final EmailSvc emailSvc;

    private final RatePosts ratesDoa;

    private final Follows followDoa;

    private final Doggo doggo = new Doggo();

    public PostController(Posts pDoa, Users usersDoa, Comments commentsDoa, EmailSvc emailSvc, RatePosts ratesDoa, Follows followDoa) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
        this.emailSvc = emailSvc;
        this.ratesDoa = ratesDoa;
        this.followDoa = followDoa;
    }

    @GetMapping(path = "/")
    public String index(Model model) {
//        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", usersDoa.getOne(cUser.getId()));
//        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
//            User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                    model.addAttribute("username", usersDoa.getOne(cUser.getId()));
//        }
        model.addAttribute("posts", Doggo.reverse(pDoa.findAll()));
        model.addAttribute("from", "home");
        return "posts/feed";
    }

    @GetMapping(path = "/posts")
    public String posts(Model model) {
//        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", usersDoa.getOne(cUser.getId()));
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("user", usersDoa.getOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
        }
        model.addAttribute("posts", Doggo.reverse(pDoa.findAll()));
        model.addAttribute("from", "feed");
        return "posts/feed";
    }

    @GetMapping(path = "/posts/{id}")
    public String post(@PathVariable String id, Model model) {
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        model.addAttribute("from", "feed");
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
        temp.setDate(new Date());
        pDoa.save(temp);
//        this.emailSvc.prepareAndSend(temp, "A new post was created!", temp.getTitle() + "\n" + temp.getBody());
        return "redirect:/posts";
    }

    @PostMapping(path = "/posts/{postId}/edit/delete-comment/{commentId}")
    @ResponseBody
    public String deleteCom(@PathVariable String postId, @PathVariable String commentId){
        commentsDoa.deleteById(Long.parseLong(commentId));
        return "";
//        return "redirect:/posts/" + postId + "/edit";
    }

    @RequestMapping(value = "/posts/{id}/like/{from}", method = RequestMethod.POST)
    @ResponseBody
    public String like(@PathVariable(name = "id") String postId){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RatePost temp = new RatePost((byte) 1, cUser.getId(), Long.parseLong(postId));
        ratesDoa.save(temp);
        return "";
    }

    @RequestMapping(value = "/follow/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public String follow(@PathVariable(name = "userId") String userId){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToFollow = usersDoa.getOne(Long.parseLong(userId));
        Follow fol = new Follow(cUser, userToFollow);
//        if (fol.getId() == null){
//            fol.setId(1L);
//        }
        followDoa.save(fol);
        return "";
    }

    @GetMapping(path = "/following")
    public String following(Model model) {
        User cUser = usersDoa.getOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        model.addAttribute("user", cUser);
        model.addAttribute("posts", processPosts(cUser.getAllFollowingPosts(), 25));
        model.addAttribute("from", "following");
        return "posts/feed";
    }


    private ArrayList<Post> processPosts(ArrayList<Post> posts, int amount){
        ArrayList<Post> sorted = new ArrayList<>();
        ArrayList<Post> temp = posts;
        while (temp.size() > 0){
            sorted.add(temp.get(indexOfNewest(temp)));
            temp.remove((int) indexOfNewest(temp));
        }
        sorted = doggo.reverse(sorted);
        for (int i = 0; i < Math.min(sorted.size(), amount); i++) {
            temp.add(sorted.get(i));
        }
        return temp;
    }

    private ArrayList<Post> processPosts(ArrayList<Post> posts, int start, int end){
        ArrayList<Post> sorted = new ArrayList<>();
        ArrayList<Post> temp = posts;
        while (temp.size() > 0){
            sorted.add(temp.get(indexOfNewest(temp)));
            temp.remove((int) indexOfNewest(temp));
        }
        sorted = doggo.reverse(sorted);
//        int limit = Math.min(sorted.size(), amount);
//        return (ArrayList<Post>) sorted.subList(0, limit);
        return sorted;
    }

    private ArrayList<Post> processPosts(ArrayList<Post> temp){
        ArrayList<Post> sorted = new ArrayList<>();
        while (temp.size() > 0){
            sorted.add(temp.get(indexOfNewest(temp)));
            temp.remove((int) indexOfNewest(temp));
        }
        sorted = doggo.reverse(sorted);
        return sorted;
    }

    private Integer indexOfNewest(ArrayList<Post> posts){
        int temp = 0;
        for(int i = 0; i < posts.size(); i++){
            if (posts.get(i).getDate().compareTo(posts.get(temp).getDate()) < 0){
                temp = i;
            }
        }
        return temp;
    }
}
