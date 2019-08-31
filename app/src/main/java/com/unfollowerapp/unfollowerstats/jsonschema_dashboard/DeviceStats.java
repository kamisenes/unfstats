package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceStats {

    @SerializedName("counts")
    @Expose
    private List<Integer> counts = null;
    @SerializedName("device_names")
    @Expose
    private List<String> deviceNames = null;

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }

    public List<String> getDeviceNames() {
        return deviceNames;
    }

    public void setDeviceNames(List<String> deviceNames) {
        this.deviceNames = deviceNames;
    }
}
