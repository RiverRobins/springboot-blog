package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.Comments;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.repositories.RateComments;
import com.codeup.springblog.repositories.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final Posts pDoa;

    private final Users usersDoa;

    private final Comments commentsDoa;

    private final RateComments ratesDoa;

    public CommentController(Posts pDoa, Users usersDoa, Comments commentsDoa, RateComments rateComments) {
        this.pDoa = pDoa;
        this.usersDoa = usersDoa;
        this.commentsDoa = commentsDoa;
        this.ratesDoa = rateComments;
    }
    @RequestMapping(path = "/posts/{id}/comment")
    @ResponseBody
    public String comment(@PathVariable(name = "id") String postId, @RequestParam(name = "body") String body) {
//        ObjectMapper m = new ObjectMapper();
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment temp = new Comment(body, usersDoa.getOne(cUser.getId()), pDoa.getOne(Long.parseLong(postId)));
        commentsDoa.save(temp);
        return "";
//        return "redirect:/posts/" + postId;
    }
    @PostMapping(path = "/posts/{id}/comment/{commentId}/like/{from}")
    @ResponseBody
    public String like(@PathVariable(name = "commentId") String commentId){
        User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RateComment temp = new RateComment((byte) 1, cUser.getId(), Long.parseLong(commentId));
        ratesDoa.save(temp);
        return "";
    }
}
