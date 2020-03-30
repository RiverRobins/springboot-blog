package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false)
    private String bio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> comments;

//    @ManyToMany
//    @JoinTable(name="follow",
//            joinColumns=@JoinColumn(name="followingId"),
//            inverseJoinColumns=@JoinColumn(name="followerId")
//    )
//    private List<User> following;
//
//    @ManyToMany
//    @JoinTable(name="follow",
//            joinColumns=@JoinColumn(name="followerId"),
//            inverseJoinColumns=@JoinColumn(name="followingId")
//    )
//    private List<User> followers;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "following")
    private List<Follow> following;

    public User(){}

    public User(User copy) {
        id = copy.id; //IMPORTANT!
        this.email = copy.email;
        this.username = copy.username;
        this.password = copy.password;
        this.bio = copy.bio;
    }

    public User(String username, String email, String password, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }

    public User(Long id, String username, String email, String password, String bio) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }

//    public List<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }
//
//    public List<User> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<User> followers) {
//        this.followers = followers;
//    }
//
//    public ArrayList<Post> getAllFollowingPosts(){
//        ArrayList<Post> all = new ArrayList<>();
//        for (User follow : this.following) {
//            all.addAll(follow.getPosts());
//        }
//        return all;
//    }

    public List<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follow> following) {
        this.following = following;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public ArrayList<Post> getAllFollowingPosts(){
        ArrayList<Post> all = new ArrayList<>();
        for (Follow follow : this.following) {
            all.addAll(follow.getFollowing().getPosts());
        }
        return all;
    }

}
