//package com.codeup.springblog.models;
//
//import org.springframework.data.annotation.Id;
//import javax.persistence.*;
//import java.util.ArrayList;
//
//@Entity
//@Table(name="users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false, length = 100)
//    private String username;
//    @Column(nullable = false, length = 100)
//    private String password;
//    @Column(nullable = false, length = 100)
//    private String bio;
//
//    public User(){}
//
//    public User(String username, String password, String bio) {
//        this.username = username;
//        this.password = password;
//        this.bio = bio;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getBio() {
//        return bio;
//    }
//
//    public void setBio(String bio) {
//        this.bio = bio;
//    }
//}
