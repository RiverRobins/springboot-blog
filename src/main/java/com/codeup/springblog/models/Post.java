package com.codeup.springblog.models;

import java.util.ArrayList;

public class Post {
    private Long id;
    private Long user_id;
    private String title;
    private String body;
    private ArrayList<Comment> comments;
    private ArrayList<Comment> topComments = new ArrayList<>();

    public Post(){}

    public Post(Long id, Long user_id, String title, String body, ArrayList<Comment> comments) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
        this.comments = comments;
        if (comments.size() > 0) {
            this.topComments.add(comments.get(0));
        }
        if (comments.size() > 1) {
            this.topComments.add(comments.get(1));
        }
        if (comments.size() > 2) {
            this.topComments.add(comments.get(2));
        }
    }

    public Post(Long user_id, String title, String body, ArrayList<Comment> comments) {
        this.user_id = user_id;
        this.title = title;
        this.body = body;
        this.comments = comments;
        if (comments.size() > 0) {
            this.topComments.add(comments.get(0));
        }
        if (comments.size() > 1) {
            this.topComments.add(comments.get(1));
        }
        if (comments.size() > 2) {
            this.topComments.add(comments.get(2));
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getTopComments() {
        return topComments;
    }

    public void setTopComments(ArrayList<Comment> topComments) {
        this.topComments = topComments;
    }
}
