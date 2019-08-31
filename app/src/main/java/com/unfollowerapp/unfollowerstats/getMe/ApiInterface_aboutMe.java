package com.unfollowerapp.unfollowerstats.getMe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface_aboutMe {
    @GET("me")
    Call<aboutMe> getData();
}
