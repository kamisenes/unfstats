package com.unfollowerapp.unfollowerstats.recyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.unfollowerapp.unfollowerstats.R;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.Datum;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Datum> Items = new ArrayList<>();
    OnItemClickListener mItemClickListener;
    Context mContext;

    public ItemAdapter(Context context) {
        Items = new ArrayList<>();
        mContext = context;
    }

    public ItemAdapter(List<Datum> items) {
        this.Items = items;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,nickName,fbRate,aboutPerson,unfdate,followerCount,followingCount,tvfirstline;
        ImageView imgview;
        Button rc_followcaes;
        ImageView locksa;

        public ItemViewHolder(View view) {
            super(view);
            name=itemView.findViewById(R.id.rc_name);
            nickName=itemView.findViewById(R.id.rc_screenname);
            fbRate=itemView.findViewById(R.id.rc_fbrate);
            aboutPerson=itemView.findViewById(R.id.rc_location);
            unfdate=itemView.findViewById(R.id.rc_actiondate);
            locksa= itemView.findViewById(R.id.img_lockcase);
            followerCount=itemView.findViewById(R.id.rc_followerscount);
            followingCount=itemView.findViewById(R.id.rc_followingcount);
            imgview=itemView.findViewById(R.id.rc_profimage);
            tvfirstline=itemView.findViewById(R.id.rc_fbrate2);
            rc_followcaes=itemView.findViewById(R.id.rc_followbtn);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_person, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Datum datum = Items.get(position);

//        holder.txtFirstName.setText("First Name: "+ model.getFirstName());
    //    holder.txtLastName.setText("Last Name: "+ model.getLastName());

        String myName,myScreenName,myAboutPerson,myActionDate;
        int myFbRate,myFollowersCount,myFollowingCount;
        boolean protected_case;
        float rate;
        boolean follow_case;
        String urlz;
        myName=datum.getName();
        myScreenName=datum.getScreenName();
        myFbRate=datum.getRate();
        myAboutPerson=datum.getDescription();
        myActionDate=datum.getActionDate();
        follow_case=datum.getFollowing();
        myFollowersCount=datum.getFollowersCount();
        myFollowingCount=datum.getFriendsCount();
        protected_case=datum.getProtected();
            if (protected_case){
                holder.locksa.setBackgroundResource(R.drawable.protect);
            }
            if(follow_case){
                holder.rc_followcaes.setText("Unfollow");
                holder.rc_followcaes.setBackgroundResource(R.color.Red);

            }
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
        return Items.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClicklListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setItems(List<Datum> items){
        Items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Datum> items){
        Items.addAll(items);
        notifyDataSetChanged();;
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
    private int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }
}
