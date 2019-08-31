package com.unfollowerapp.unfollowerstats.jsonschema_unfollowers;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface_getMuted_withp {
    @POST("muted")
    Call<data_Unfollowers> getData(@Query("p") int page);
}
