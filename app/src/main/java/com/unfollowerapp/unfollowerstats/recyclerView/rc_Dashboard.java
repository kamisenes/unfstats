package com.unfollowerapp.unfollowerstats.recyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unfollowerapp.unfollowerstats.Person;
import com.unfollowerapp.unfollowerstats.R;
import com.unfollowerapp.unfollowerstats.fragments.Dashboard;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.Datum;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class rc_Dashboard extends RecyclerView.Adapter<rc_Dashboard.MyviewHolder> {
    LayoutInflater inflater;
    List<Datum> datumArrayList;
    Context context;

    public rc_Dashboard(Context c, List<Datum> arrayList){
        this.context=c;
        inflater = LayoutInflater.from(c);
        this.datumArrayList=arrayList;
    }
    @NonNull
    @Override
    public rc_Dashboard.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_person, parent, false);
        rc_Dashboard.MyviewHolder holder = new rc_Dashboard.MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull rc_Dashboard.MyviewHolder holder, int position) {

        String myName,myScreenName,myAboutPerson,myActionDate;
        int myFbRate,myFollowersCount,myFollowingCount;
        String urlz;
        Datum datum=datumArrayList.get(position);
        myName=datum.getName();
        myScreenName=datum.getScreenName();
        myFbRate=datum.getRate();
        myAboutPerson=datum.getDescription();
        myActionDate=datum.getActionDate();
        myFollowersCount=datum.getFollowersCount();
        myFollowingCount=datum.getFriendsCount();
        urlz=datum.getProfileImageUrlHttps();
        holder.name.setText(myName);
        holder.nickName.setText(myScreenName);
        holder.fbRate.setText(String.valueOf(myFbRate)+"%");
        holder.fbRate.setBackgroundResource(R.color.Green);
        holder.fbRate.getLayoutParams().width=myFbRate*2;
        holder.tvfirstline.getLayoutParams().width=200;
        holder.aboutPerson.setText(myAboutPerson);
        holder.unfdate.setText("Unfollowed Date: "+myActionDate);
        holder.followerCount.setText(String.valueOf(myFollowersCount));
        holder.followingCount.setText(String.valueOf(myFollowingCount));
        new DownloadImageTask(holder.imgview).execute(urlz);
    }

    @Override
    public int getItemCount() {
        return  datumArrayList == null ? 0 : datumArrayList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView name,nickName,fbRate,aboutPerson,unfdate,followerCount,followingCount,tvfirstline;
        ImageView imgview;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.rc_name);
            nickName=itemView.findViewById(R.id.rc_screenname);
            fbRate=itemView.findViewById(R.id.rc_fbrate);
            aboutPerson=itemView.findViewById(R.id.rc_location);
            unfdate=itemView.findViewById(R.id.rc_actiondate);
            followerCount=itemView.findViewById(R.id.rc_followerscount);
            followingCount=itemView.findViewById(R.id.rc_followingcount);
            tvfirstline=itemView.findViewById(R.id.rc_fbrate2);

            imgview=itemView.findViewById(R.id.rc_profimage);

        }

    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
        this.bmImage=bmImage;
        }

        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Log.d("imgurl",urldisplay);
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
            bmImage.setImageBitmap(result);
        }
    }
}
