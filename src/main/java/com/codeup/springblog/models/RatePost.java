package com.codeup.springblog.models;

import javax.persistence.*;


@Entity
@Table(name="post_rates")
public class RatePost extends Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Byte value = 0;
    @Column(nullable = false)
    private Long post_id;
    @Column(nullable = false)
    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    public RatePost(){}

    public RatePost(Byte value, User user, Post post) {
        this.value = value;
        this.user = user;
        this.post = post;
    }

    public RatePost(Long id, Byte value, User user, Post post) {
        this.id = id;
        this.value = value;
        this.user = user;
        this.post = post;
    }

    public RatePost(Byte value, Long user_id, Long post_id) {
        this.value = value;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public RatePost(Long id, Byte value, Long user_id, Long post_id) {
        this.id = id;
        this.value = value;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
