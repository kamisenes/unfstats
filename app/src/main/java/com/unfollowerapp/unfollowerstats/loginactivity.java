package com.unfollowerapp.unfollowerstats;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

import static com.unfollowerapp.unfollowerstats.MainActivity.MY_PREFS_NAME;

public class loginactivity extends AppCompatActivity {


    Context mCon;
    private WebView webView;
    SharedPreferences prefs;
    private String token="user_token";
    private  String Device_Names;
    String compURL="https://unfollowerstats.com/complete";

    String loginURL="https://unfollowerstats.com/redirect-api?device=";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        webView = (WebView) findViewById(R.id.webview);
        mCon=getApplicationContext();
         prefs = getSharedPreferences(
                 MY_PREFS_NAME, Context.MODE_PRIVATE);


        Device_Names=getDeviceName();
        String new_URL=loginURL+Device_Names;
        webView.getSettings().setAppCacheEnabled(false);
        webView.clearCache(true);
        webView.clearHistory();
        clearCookies(mCon);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(new_URL);

        Log.d("getcookiess_URL",new_URL+"");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                String str=getCookie(compURL,token);
                Log.d("getcookiess_CompURL:",compURL+"");
                Log.d("getcookiess_onpagefin",str+"");



            }

        });

    }

    public String getCookie(String siteName,String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);

        if(cookies != null){
            Log.d("getcookis_fonksiyonici",cookies+"");

            parseData(cookies);

            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(CookieName)){
                    String[] temp1=ar1.split("=");
                    CookieValue = temp1[1];
                }
            }
        }
        return CookieValue;
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public  void clearCache() {
        new WebView(mCon).clearCache(true);
    }

    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }
    public void parseData(String str){

            int pos=1;
            String deger=str;
            String[] temp=deger.split(";");

            for (String ar1 : temp ){
                String CookieName="user_";
                if(ar1.contains(CookieName)){
                    String[] temp1=ar1.split("=");
                    Log.d("getcookis_parseislemi",temp1[1]);
                    String data=temp1[1];
                    saveData(data,pos);
                    pos++;
                }

        }

    }
    public void saveData(String str,int position){
        SharedPreferences.Editor editor = prefs.edit();

        if (position==1){
        editor.putString("user_token", str);
        editor.apply();
        }
        else if (position==2){
            editor.putString("user_token_secret", str);
            editor.apply();
        }
        else{
            editor.putString("user_id", str);
            editor.apply();
            Intent intent=new Intent(loginactivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        editor.putString("logincase","logged");
        editor.putString("device_name",Device_Names);
        editor.apply();
    }
}
