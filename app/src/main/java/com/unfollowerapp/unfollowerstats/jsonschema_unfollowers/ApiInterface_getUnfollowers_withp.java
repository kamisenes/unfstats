package com.unfollowerapp.unfollowerstats.jsonschema_unfollowers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface_getUnfollowers_withp {
    @POST("unfollowers")
    Call<data_Unfollowers> getData(@Query("p") int  page);
}
