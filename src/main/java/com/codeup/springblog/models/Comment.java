package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long post_id;
    @Column(nullable = false)
    private Long user_id;
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<RateComment> rates = new ArrayList<>();

    public Post getPost() {
        return this.post;
    }

    public User getUser() {
        return this.user;
    }

    public Comment(){}

    public Comment(String body, User user, Post post) {
        this.body = body;
        this.user = user;
        this.post = post;
        this.user_id = this.user.getId();
        this.post_id = this.post.getId();
    }

    public Comment(Long user_id, String text) {
        this.user_id = user_id;
        this.body = text;
    }

    public Comment(Long id, Long user_id, String text) {
        this.id = id;
        this.user_id = user_id;
        this.body = text;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getBody() {
        return body;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<RateComment> getRates() {
        return rates;
    }

    public void setRates(List<RateComment> rates) {
        this.rates = rates;
    }

    public int getRating(){
        int temp = 0;
        if (this.rates != null) {
            for (RateComment r : this.rates) {
                temp += r.getValue();
            }
        }
        return temp;
    }
}
