package com.unfollowerapp.unfollowerstats.jsonschema_dashboard;

import com.unfollowerapp.unfollowerstats.getMe.aboutMe;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface_getDashboard {
    @GET("dashboard")
    Call<data_Dashboard> getData();
}
