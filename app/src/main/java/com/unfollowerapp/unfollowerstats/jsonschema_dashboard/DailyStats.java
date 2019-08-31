package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyStats {

    @SerializedName("names")
    @Expose
    private List<String> names = null;
    @SerializedName("counts")
    @Expose
    private List<Integer> counts = null;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }
}

