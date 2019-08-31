package com.unfollowerapp.unfollowerstats;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.unfollowerapp.unfollowerstats.fragments.Blocked;
import com.unfollowerapp.unfollowerstats.fragments.Dashboard;
import com.unfollowerapp.unfollowerstats.fragments.Followers;
import com.unfollowerapp.unfollowerstats.fragments.Following;
import com.unfollowerapp.unfollowerstats.fragments.I_Dont_Follow_Back;
import com.unfollowerapp.unfollowerstats.fragments.Muted;
import com.unfollowerapp.unfollowerstats.fragments.Non_Follow_Back;
import com.unfollowerapp.unfollowerstats.fragments.Unfollowers;
import com.unfollowerapp.unfollowerstats.getMe.ApiClient_aboutme;
import com.unfollowerapp.unfollowerstats.getMe.ApiInterface_aboutMe;
import com.unfollowerapp.unfollowerstats.getMe.Me;
import com.unfollowerapp.unfollowerstats.getMe.aboutMe;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.ApiInterface_getDashboard;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.data_Dashboard;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.ApiInterface_getFollowers_withp;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.ApiInterface_getUnfollowers;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.ApiInterface_getUnfollowers_withp;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.Datum;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.data_Unfollowers;
import com.unfollowerapp.unfollowerstats.recyclerView.rc_Dashboard;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences sharedPreferences;
    public static String user_token;
    public static String user_token_secret;
    public static String user_id;
    public static double api_version = 1.0;


    aboutMe aboutMeCall;
    Me mydata;

    data_Dashboard dashboardCall;
    data_Unfollowers unfollowersCall;

    private int data_c;
    private int pc;

    private int followers_data_c;
    private int followers_pc;



    public static String device_token = "abc";
    public static String device_name;


    ApiInterface_aboutMe api_interface;
    ApiInterface_getDashboard api_interfaceDashboard;
    ApiInterface_getUnfollowers apiInterface_getUnfollowers;
    ApiInterface_getUnfollowers_withp apiface;
    ApiInterface_getFollowers_withp apiface_getfollowers;
    Context context;
    URL url;
    ImageView imgvw;
    private List<Datum> d_list;

    private data_Unfollowers du;
    TextView tvnav_name, tv_nav_nickname, tvnav_followunfcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        context = getApplicationContext();

        View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        imgvw = (ImageView) hView.findViewById(R.id.nav_headerimage);
        tvnav_name = (TextView) hView.findViewById(R.id.nav_header_name);
        tv_nav_nickname = (TextView) hView.findViewById(R.id.nav_header_nickname);
        tvnav_followunfcount = (TextView) hView.findViewById(R.id.nav_header_folowunf);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        api_interface = ApiClient_aboutme.getClient().create(ApiInterface_aboutMe.class);
        api_interfaceDashboard = ApiClient_aboutme.getClient().create(ApiInterface_getDashboard.class);
        apiInterface_getUnfollowers = ApiClient_aboutme.getClient().create(ApiInterface_getUnfollowers.class);
        apiface = ApiClient_aboutme.getClient().create(ApiInterface_getUnfollowers_withp.class);
        apiface_getfollowers=ApiClient_aboutme.getClient().create(ApiInterface_getFollowers_withp.class);
        sharedPreferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String isLogin = sharedPreferences.getString("logincase", null);

        if (isLogin == null) {
            Intent intent = new Intent(MainActivity.this, loginactivity.class);
            startActivity(intent);
            finish();
        } else {

            user_token = sharedPreferences.getString("user_token", null);
            user_token_secret = sharedPreferences.getString("user_token_secret", null);
            user_id = sharedPreferences.getString("user_id", null);
            device_name = sharedPreferences.getString("device_name", null);
            Toast.makeText(this, "user_token:" + user_token
                            + "user_token_secret:" + user_token_secret
                            + "user_id:" + user_id
                            + "device_name:" + device_name
                            + "Login Case:" + "Logged"
                    , Toast.LENGTH_SHORT).show();
            Log.d("tokendatamm", "user_token:" + user_token
                    + "user_token_secret:" + user_token_secret
                    + "user_id:" + user_id
                    + "device_name:" + device_name);
            new AsyncCaller().execute();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_dashboard) {
            fragment = new Dashboard(context, aboutMeCall, dashboardCall, unfollowersCall);
            displaySelectedFragment(fragment);
            // Handle the camera action
        } else if (id == R.id.nav_followers) {
            fragment = new Followers(context);
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_following) {
            fragment = new Following(context);
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_unfollowers) {
            fragment = new Unfollowers(context);
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_idontfollowback) {
            fragment = new I_Dont_Follow_Back(context);
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_nonfollowback) {
            fragment = new Non_Follow_Back(context);
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.nav_blocked) {
            fragment = new Blocked(context);
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.nav_muted) {
            fragment = new Muted(context);
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.nav_updatedata) {

        }
        else if (id == R.id.nav_logout) {

        }
        else if (id == R.id.nav_settings) {

        }
        else if (id == R.id.nav_payment) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


            Call<aboutMe> call = api_interface.getData();
            call.enqueue(new Callback<aboutMe>() {
                @Override
                public void onResponse(Call<aboutMe> call, Response<aboutMe> response) {
                    Log.d("responsecode::", response.code() + ".....");
                    if (response.code() == 200) {
                        aboutMeCall = response.body();
                        mydata = aboutMeCall.getMe();
                        String name = mydata.getName();
                        String nickname = mydata.getScreenName();
                        tvnav_name.setText(name);
                        tv_nav_nickname.setText(nickname);
                        String imgurl = mydata.getProfileImage();
                        new DownloadImageTask(imgvw).execute(imgurl);
                        tvnav_followunfcount.setText(mydata.getFollowing() + "Following " + "|" + mydata.getFollowers() + "Followers");
                        Log.d("responsecode_name::", name + ".....");

                    }
                }

                @Override
                public void onFailure(Call<aboutMe> call, Throwable t) {
                    Log.d("responsecode:hata", t.getMessage() + ".....");

                }
            });


            Call<data_Dashboard> call_data = api_interfaceDashboard.getData();
            call_data.enqueue(new Callback<data_Dashboard>() {
                @Override
                public void onResponse(Call<data_Dashboard> call, Response<data_Dashboard> response) {
                    if (response.code() == 200) {
                        dashboardCall = response.body();
                        //dashboardcall i dashboard fragmentine yolla
                    }
                }

                @Override
                public void onFailure(Call<data_Dashboard> call, Throwable t) {

                }
            });
            Call<data_Unfollowers> call_unf = apiInterface_getUnfollowers.getData();
            call_unf.enqueue(new Callback<data_Unfollowers>() {
                @Override
                public void onResponse(Call<data_Unfollowers> call, Response<data_Unfollowers> response) {

                    if (response.code() == 200) {
                        unfollowersCall = response.body();
                        assert unfollowersCall != null;
                        if (unfollowersCall.getPageCount() != null) {
                            pc = unfollowersCall.getPageCount();
                            data_c=unfollowersCall.getDataCount();



                        }
                        Log.d("unfollowerscall", response.code() + "...");
                    }
                }

                @Override
                public void onFailure(Call<data_Unfollowers> call, Throwable t) {
                    Log.d("unfollowerscallhata", t.getMessage() + "...");

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            pdLoading.dismiss();


        }

    }

    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {

        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Log.d("imgurl", urldisplay);
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            imgvw.setImageBitmap(result);
        }
    }
}

