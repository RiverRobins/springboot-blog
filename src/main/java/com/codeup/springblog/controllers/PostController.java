package com.codeup.springblog.controllers;
//import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.Posts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final Posts pDoa;

    public PostController(Posts p) {
        this.pDoa = p;
    }

    @GetMapping(path = "/posts")
    public String posts(Model model) {
        model.addAttribute("posts", pDoa.findAll());
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String post(@PathVariable String id, Model model) {
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        return "posts/post-view";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String editGet(@PathVariable String id, Model model){
        Post old = pDoa.getOne(Long.parseLong(id));
        model.addAttribute("post", old);
        return "posts/post-edit";
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String editPost(@PathVariable String id, Model model, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post old = pDoa.getOne(Long.parseLong(id));
        Post temp = new Post( old.getId(), old.getUserId(), title, body);
        pDoa.save(temp);
        model.addAttribute("post", pDoa.getOne(Long.parseLong(id)));
        return "posts/post-view";
    }

    @PostMapping(path = "/posts/{id}/delete")
    public String delete(@PathVariable String id){
        pDoa.deleteById(Long.parseLong(id));
        return "redirect:/posts";
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
