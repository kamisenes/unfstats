package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TweetAnalyze {

    @SerializedName("retweets")
    @Expose
    private Integer retweets;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("per_day")
    @Expose
    private Double perDay;

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Double getPerDay() {
        return perDay;
    }

    public void setPerDay(Double perDay) {
        this.perDay = perDay;
    }
}
