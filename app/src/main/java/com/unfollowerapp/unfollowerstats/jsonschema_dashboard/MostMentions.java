package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostMentions {
    @SerializedName("mention_counts")
    @Expose
    private List<Integer> mentionCounts = null;
    @SerializedName("usernames")
    @Expose
    private List<String> usernames = null;

    public List<Integer> getMentionCounts() {
        return mentionCounts;
    }

    public void setMentionCounts(List<Integer> mentionCounts) {
        this.mentionCounts = mentionCounts;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
