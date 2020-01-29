//package com.codeup.springblog.models;
//
//import org.springframework.data.annotation.Id;
//import javax.persistence.*;
//
//
//@Entity
//@Table(name="comments")
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    private Long post_id;
//    @Column(nullable = false)
//    private Long user_id;
//    @Column(nullable = false)
//    private String text;
//
//    public Comment(){}
//
//    public Comment(Long post_id, Long user_id, String text) {
//        this.post_id = post_id;
//        this.user_id = user_id;
//        this.text = text;
//    }
//
//    public Comment(Long id, Long post_id, Long user_id, String text) {
//        this.id = id;
//        this.post_id = post_id;
//        this.user_id = user_id;
//        this.text = text;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Long getPost_id() {
//        return post_id;
//    }
//
//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//}
