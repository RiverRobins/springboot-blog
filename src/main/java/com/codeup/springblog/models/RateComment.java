package com.codeup.springblog.models;

import javax.persistence.*;


@Entity
@Table(name="comment_rates")
public class RateComment extends Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Byte value = 0;
    @Column(nullable = false)
    private Long comment_id;
    @Column(nullable = false)
    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Comment comment;

    public RateComment(){}

    public RateComment(Byte value, User user, Comment comment) {
        this.value = value;
        this.user = user;
        this.comment = comment;
    }

    public RateComment(Long id, Byte value, User user, Comment post) {
        this.id = id;
        this.value = value;
        this.user = user;
        this.comment = post;
    }

    public RateComment(Byte value, Long user_id, Long comment_id) {
        this.value = value;
        this.comment_id = comment_id;
        this.user_id = user_id;
    }

    public RateComment(Long id, Byte value, Long user_id, Long comment_id) {
        this.id = id;
        this.value = value;
        this.comment_id = comment_id;
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

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
