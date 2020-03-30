package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
public class Follow {

    @ManyToOne
    @JoinColumn(name = "follower_id", insertable=false, updatable=false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", insertable=false, updatable=false)
    private User following;

    public Follow(User follower, User following) {
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
}
