package com.unfollowerapp.unfollowerstats.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unfollowerapp.unfollowerstats.PaginationScrollListener;
import com.unfollowerapp.unfollowerstats.R;
import com.unfollowerapp.unfollowerstats.getMe.ApiClient_aboutme;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.ApiInterface_getFollowers_withp;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.ApiInterface_getFollowing_withp;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.Datum;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.data_Unfollowers;
import com.unfollowerapp.unfollowerstats.recyclerView.ItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Followers extends Fragment {

        Context context;
    private RecyclerView mRecyclerView;
        private data_Unfollowers du;


        private List<Datum> d_list;
        private ItemAdapter mAdapter;
        private ProgressBar progressBar;

        private boolean isLoading = false;
        private boolean isLastPage = false;
        private int page = 1;


        private ApiInterface_getFollowers_withp apiface;

        private List<Datum> d_l;
        public Followers(Context c ){
        this.context=c;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.followers,null);
        Objects.requireNonNull(getActivity()).setTitle("Followers");
        d_list=new ArrayList<>();
        apiface=ApiClient_aboutme.getClient().create(ApiInterface_getFollowers_withp.class);


            try {

                mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_followers);
                progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                mRecyclerView.setLayoutManager(linearLayoutManager);

                mAdapter = new ItemAdapter(context);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                    @Override
                    protected void loadMoreItems() {
                        isLoading = true;
                        if (!isLastPage) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadData(page);
                                }
                            }, 200);
                        }
                    }

                    @Override
                    public boolean isLastPage() {
                        return isLastPage;
                    }

                    @Override
                    public boolean isLoading() {
                        return isLoading;
                    }
                });

                mAdapter.setOnItemClicklListener(new ItemAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(context, "Clicked item position: " + position, Toast.LENGTH_LONG).show();
                    }
                });

                loadData(page);
            } catch (Exception ex) {
                Log.e("hata", ex.getMessage());
            }


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    private void loadData(int page) {
        Call<data_Unfollowers> dacall=apiface.getData(page);
        dacall.enqueue(new Callback<data_Unfollowers>() {
            @Override
            public void onResponse(Call<data_Unfollowers> call, Response<data_Unfollowers> response) {
                if (response.code()==200){
                    du=response.body();
                    if (du.getDataCount()==0){
                        setDialog("There is no Followers yet, please check later");
                    }else
                        {
                        resultAction(du);
                    }
                }
            }

            @Override
            public void onFailure(Call<data_Unfollowers> call, Throwable t) {

            }
        });

    }

    private void resultAction(data_Unfollowers model) {
        progressBar.setVisibility(View.INVISIBLE);
        isLoading = false;
        if (model != null) {
            mAdapter.addItems(model.getData());
            if (model.getCurrentPage() == model.getPageCount()) {
                isLastPage = true;
            } else
                {
                page = model.getCurrentPage() + 1;
            }
        }
    }
    private void setDialog(String message){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle( "Notice" )
                .setMessage(message)

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {

                    }
                }).show();
    }
    }



