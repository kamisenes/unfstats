package com.unfollowerapp.unfollowerstats.getMe;

import com.unfollowerapp.unfollowerstats.MainActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.unfollowerapp.unfollowerstats.MainActivity.api_version;
import static com.unfollowerapp.unfollowerstats.MainActivity.device_token;
import static com.unfollowerapp.unfollowerstats.MainActivity.user_id;
import static com.unfollowerapp.unfollowerstats.MainActivity.user_token;
import static com.unfollowerapp.unfollowerstats.MainActivity.user_token_secret;

public class ApiClient_aboutme
{
    public static OkHttpClient httpClient=new OkHttpClient.Builder().addInterceptor(
            new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request orjinal=chain.request();
                    Request request=orjinal.newBuilder()
                            .header("user_token",user_token)
                            .header("user_token_secret",user_token_secret)
                            .header("user_id",user_id)
                            .header("api_version", String.valueOf(api_version))
                            .header("device_token",device_token)
                            .method(orjinal.method(),orjinal.body())
                            .build();

                    return  chain.proceed(request);
                }

            }
    ).build();


    private static Retrofit retrofit=null;
    private static String URL="http://ios-api.unfollowerstats.com/get/";
    public static Retrofit getClient() {
        if (retrofit==null){

            retrofit=new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();

            return  retrofit;
        }

        return  retrofit;

    }
}
