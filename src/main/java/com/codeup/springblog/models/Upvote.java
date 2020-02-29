package com.codeup.springblog.models;

import javax.persistence.*;


@Entity
@Table(name="upvotes")
public class Upvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long parent_id;
    @Column(nullable = false)
    private Long parent_type;
    @Column(nullable = false)
    private Long user_id;
    @Column(nullable = false)
    private Integer value;

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

    public Upvote(Long parent_id, Long parent_type, Long user_id, Integer value, User user, Post post) {
        this.parent_id = parent_id;
        this.parent_type = parent_type;
        this.user_id = user_id;
        this.value = value;
        this.user = user;
        this.post = post;
    }

}
