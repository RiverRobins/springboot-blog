package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
public class Follow {

//    @EmbeddedId
//    private FollowKey followKey;

    @ManyToOne
//    @MapsId("follower_id")
    @JoinColumn(name = "follower_id", insertable=false, updatable=false)
    private User follower;

    @ManyToOne
//    @MapsId("following_id")
    @JoinColumn(name = "following_id", insertable=false, updatable=false)
    private User following;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    //    public Follow(FollowKey followKey, User follower, User following) {
//        this.followKey = followKey;
//        this.follower = follower;
//        this.following = following;
//    }
//
//    public FollowKey getFollowKey() {
//        return followKey;
//    }
//
//    public void setFollowKey(FollowKey followKey) {
//        this.followKey = followKey;
//    }

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
