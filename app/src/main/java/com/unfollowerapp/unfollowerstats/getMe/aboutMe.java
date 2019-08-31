package com.unfollowerapp.unfollowerstats.getMe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class aboutMe {
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
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("auth")
    @Expose
    private Boolean auth;
    @SerializedName("me")
    @Expose
    private Me me;

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

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }
}
