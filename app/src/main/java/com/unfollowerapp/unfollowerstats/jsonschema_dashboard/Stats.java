package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("newFollowers")
    @Expose
    private Integer newFollowers;
    @SerializedName("newFollowing")
    @Expose
    private Integer newFollowing;
    @SerializedName("newUnfollowers")
    @Expose
    private Integer newUnfollowers;
    @SerializedName("UnfollowersCount")
    @Expose
    private Integer unfollowersCount;
    @SerializedName("newNonFollowers")
    @Expose
    private Integer newNonFollowers;
    @SerializedName("newIDontFollowBack")
    @Expose
    private Integer newIDontFollowBack;
    @SerializedName("newMuted")
    @Expose
    private Integer newMuted;
    @SerializedName("newBlocked")
    @Expose
    private Integer newBlocked;

    public Integer getNewFollowers() {
        return newFollowers;
    }

    public void setNewFollowers(Integer newFollowers) {
        this.newFollowers = newFollowers;
    }

    public Integer getNewFollowing() {
        return newFollowing;
    }

    public void setNewFollowing(Integer newFollowing) {
        this.newFollowing = newFollowing;
    }

    public Integer getNewUnfollowers() {
        return newUnfollowers;
    }

    public void setNewUnfollowers(Integer newUnfollowers) {
        this.newUnfollowers = newUnfollowers;
    }

    public Integer getUnfollowersCount() {
        return unfollowersCount;
    }

    public void setUnfollowersCount(Integer unfollowersCount) {
        this.unfollowersCount = unfollowersCount;
    }

    public Integer getNewNonFollowers() {
        return newNonFollowers;
    }

    public void setNewNonFollowers(Integer newNonFollowers) {
        this.newNonFollowers = newNonFollowers;
    }

    public Integer getNewIDontFollowBack() {
        return newIDontFollowBack;
    }

    public void setNewIDontFollowBack(Integer newIDontFollowBack) {
        this.newIDontFollowBack = newIDontFollowBack;
    }

    public Integer getNewMuted() {
        return newMuted;
    }

    public void setNewMuted(Integer newMuted) {
        this.newMuted = newMuted;
    }

    public Integer getNewBlocked() {
        return newBlocked;
    }

    public void setNewBlocked(Integer newBlocked) {
        this.newBlocked = newBlocked;
    }
}
