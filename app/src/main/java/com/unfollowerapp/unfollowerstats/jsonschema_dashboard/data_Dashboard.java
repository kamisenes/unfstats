package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_Dashboard {

    @SerializedName("time1")
    @Expose
    private String time1;
    @SerializedName("time2")
    @Expose
    private String time2;
    @SerializedName("time31")
    @Expose
    private String time31;
    @SerializedName("time32")
    @Expose
    private String time32;
    @SerializedName("time41")
    @Expose
    private String time41;
    @SerializedName("time42")
    @Expose
    private String time42;
    @SerializedName("time51")
    @Expose
    private String time51;
    @SerializedName("time52")
    @Expose
    private String time52;
    @SerializedName("tweetAnalyze")
    @Expose
    private TweetAnalyze tweetAnalyze;
    @SerializedName("followerUnfollower")
    @Expose
    private FollowerUnfollower followerUnfollower;
    @SerializedName("bestFriends")
    @Expose
    private BestFriends bestFriends;
    @SerializedName("mostMentions")
    @Expose
    private MostMentions mostMentions;
    @SerializedName("deviceStats")
    @Expose
    private DeviceStats deviceStats;
    @SerializedName("dailyStats")
    @Expose
    private DailyStats dailyStats;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("haveJob")
    @Expose
    private Boolean haveJob;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("auth")
    @Expose
    private Boolean auth;
    @SerializedName("need_sync")
    @Expose
    private Boolean needSync;

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime31() {
        return time31;
    }

    public void setTime31(String time31) {
        this.time31 = time31;
    }

    public String getTime32() {
        return time32;
    }

    public void setTime32(String time32) {
        this.time32 = time32;
    }

    public String getTime41() {
        return time41;
    }

    public void setTime41(String time41) {
        this.time41 = time41;
    }

    public String getTime42() {
        return time42;
    }

    public void setTime42(String time42) {
        this.time42 = time42;
    }

    public String getTime51() {
        return time51;
    }

    public void setTime51(String time51) {
        this.time51 = time51;
    }

    public String getTime52() {
        return time52;
    }

    public void setTime52(String time52) {
        this.time52 = time52;
    }

    public TweetAnalyze getTweetAnalyze() {
        return tweetAnalyze;
    }

    public void setTweetAnalyze(TweetAnalyze tweetAnalyze) {
        this.tweetAnalyze = tweetAnalyze;
    }

    public FollowerUnfollower getFollowerUnfollower() {
        return followerUnfollower;
    }

    public void setFollowerUnfollower(FollowerUnfollower followerUnfollower) {
        this.followerUnfollower = followerUnfollower;
    }

    public BestFriends getBestFriends() {
        return bestFriends;
    }

    public void setBestFriends(BestFriends bestFriends) {
        this.bestFriends = bestFriends;
    }

    public MostMentions getMostMentions() {
        return mostMentions;
    }

    public void setMostMentions(MostMentions mostMentions) {
        this.mostMentions = mostMentions;
    }

    public DeviceStats getDeviceStats() {
        return deviceStats;
    }

    public void setDeviceStats(DeviceStats deviceStats) {
        this.deviceStats = deviceStats;
    }

    public DailyStats getDailyStats() {
        return dailyStats;
    }

    public void setDailyStats(DailyStats dailyStats) {
        this.dailyStats = dailyStats;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Boolean getHaveJob() {
        return haveJob;
    }

    public void setHaveJob(Boolean haveJob) {
        this.haveJob = haveJob;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Boolean getNeedSync() {
        return needSync;
    }

    public void setNeedSync(Boolean needSync) {
        this.needSync = needSync;
    }
}
