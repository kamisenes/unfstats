package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowerUnfollower {

    @SerializedName("dates")
    @Expose
    private List<String> dates = null;
    @SerializedName("followers")
    @Expose
    private List<Integer> followers = null;
    @SerializedName("unfollowers")
    @Expose
    private List<Integer> unfollowers = null;

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getUnfollowers() {
        return unfollowers;
    }

    public void setUnfollowers(List<Integer> unfollowers) {
        this.unfollowers = unfollowers;
    }
}
