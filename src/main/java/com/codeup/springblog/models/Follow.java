package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "follower_id")
    private Long followerId;
    @Column(nullable = false, name = "following_id")
    private Long followingId;

    @ManyToOne
    @JoinColumn(name = "follower_id", insertable=false, updatable=false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", insertable=false, updatable=false)
    private User following;

    public Follow(){}

    public Follow(Long id, User follower, User following) {
        this.id = id;
        this.followerId = follower.getId();
        this.followingId = following.getId();
        this.follower = follower;
        this.following = following;
    }

    public Follow(User follower, User following) {
        this.followerId = follower.getId();
        this.followingId = following.getId();
        this.follower = follower;
        this.following = following;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}
