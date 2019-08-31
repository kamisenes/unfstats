package com.unfollowerapp.unfollowerstats.jsonschema_unfollowers;

import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.data_Dashboard;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface_getUnfollowers {
    @GET("unfollowers")
    Call<data_Unfollowers> getData();
}
