package com.codeup.springblog.models;

import javax.persistence.*;


@Entity
@Table(name="upvotes")
public class Upvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Byte value = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    public Post getPost() {
        return this.post;
    }

    public User getUser() {
        return this.user;
    }

    public Upvote(){}

    public Upvote(Byte value, User user, Post post) {
        this.value = value;
        this.user = user;
        this.post = post;
    }


}
