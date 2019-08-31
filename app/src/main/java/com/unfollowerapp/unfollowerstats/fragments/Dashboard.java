package com.unfollowerapp.unfollowerstats.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.unfollowerapp.unfollowerstats.R;
import com.unfollowerapp.unfollowerstats.getMe.Me;
import com.unfollowerapp.unfollowerstats.getMe.aboutMe;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.BestFriends;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.DailyStats;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.DeviceStats;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.FollowerUnfollower;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.MostMentions;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.Stats;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.TweetAnalyze;
import com.unfollowerapp.unfollowerstats.jsonschema_dashboard.data_Dashboard;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.Datum;
import com.unfollowerapp.unfollowerstats.jsonschema_unfollowers.data_Unfollowers;
import com.unfollowerapp.unfollowerstats.recyclerView.rc_Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Dashboard  extends Fragment {

    Context c;
    private  Me mydata;

    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    private DailyStats dailyStats;
    ArrayList PieEntryLabels;
    private aboutMe aboutMe;
    private data_Dashboard dashboard;
    private data_Unfollowers data_unfollowers;
    private RecyclerView rv;
    private FollowerUnfollower followerUnfollower;
    private BestFriends bestFriends;
    private List<Datum> datum;
    private MostMentions mostMentions;
    private  TweetAnalyze tweetAnalyze;
    private Stats stats;
    PieChart pieChart ;
    PieChart daily_pieChart ;
    private DeviceStats deviceStats;
    public  Dashboard(Context context, aboutMe aboutMeCall, data_Dashboard dashboardCall, data_Unfollowers unfollowersCall){

        this.c=context;
        this.dashboard=dashboardCall;
        this.aboutMe=aboutMeCall;
        this.data_unfollowers=unfollowersCall;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard,null);
        Objects.requireNonNull(getActivity()).setTitle("DASHBOARD");
       datum=data_unfollowers.getData();
        rv= (RecyclerView) view.findViewById(R.id.rc );
        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(new rc_Dashboard(c,datum));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart= view.findViewById(R.id.pieChart);
        HorizontalBarChart horizontalChart = (HorizontalBarChart) view.findViewById(R.id.horizontal_bfchart);
        HorizontalBarChart horizontalChart_mm = (HorizontalBarChart) view.findViewById(R.id.horizontal_mmchart);
        daily_pieChart=(PieChart)view.findViewById(R.id.dailypieChart);

        bestFriends=dashboard.getBestFriends();
        mostMentions=dashboard.getMostMentions();
        final String[] monthshor = new String[bestFriends.getUsernames().size()];
        for (int i =0;i<bestFriends.getUsernames().size();i++){
            monthshor[i]=bestFriends.getUsernames().get(i);
        }


        final String[] monthshor_mm = new String[mostMentions.getUsernames().size()];
        for (int i =0;i<mostMentions.getUsernames().size();i++){
            monthshor_mm[i]=mostMentions.getUsernames().get(i);
        }




        BarDataSet barDataSet = new BarDataSet(getData(bestFriends), "Best Friends");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(Color.BLUE);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis_hori = horizontalChart.getXAxis();
        xAxis_hori.setPosition(XAxis.XAxisPosition.BOTTOM);
        IndexAxisValueFormatter formatter_hori = new IndexAxisValueFormatter(monthshor);
        xAxis_hori.setGranularity(1f);
        xAxis_hori.setValueFormatter(formatter_hori);
        horizontalChart.setData(barData);
        horizontalChart.setFitBars(true);
        horizontalChart.animateXY(5000, 5000);
        horizontalChart.invalidate();






        BarDataSet barDataSet_mm = new BarDataSet(getData_mm(mostMentions), "Most Mentions");
        barDataSet_mm.setBarBorderWidth(0.9f);
        barDataSet_mm.setColors(Color.BLUE);
        BarData barData_mm = new BarData(barDataSet_mm);
        XAxis xAxis_hori_mm = horizontalChart_mm.getXAxis();
        xAxis_hori_mm.setPosition(XAxis.XAxisPosition.BOTTOM);
        IndexAxisValueFormatter formatter_hori_mm = new IndexAxisValueFormatter(monthshor_mm);
        xAxis_hori_mm.setGranularity(1f);
        xAxis_hori_mm.setValueFormatter(formatter_hori_mm);
        horizontalChart_mm.setData(barData_mm);
        horizontalChart_mm.setFitBars(true);
        horizontalChart_mm.animateXY(5000, 5000);
        horizontalChart_mm.invalidate();






        Description description = new Description();
        description.setText(getString(R.string.pie_chart));
        horizontalChart.setDescription(description);
        horizontalChart_mm.setDescription(description);



        TextView tv_followers,tv_following,tv_tweetsent,tv_joindate;
        TextView tv_muted,tv_idontfollowback,tv_blocked,tv_unfollowers,tv_nonfollowback;
        TextView analyz_tv_retweet,analyz_tv_likes,analyz_tv_average;
        TextView tv_new_unfollowers,tv_new_followers,tv_new_following;

        LineChart chart = (LineChart) view.findViewById(R.id.linechart);





        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();






        followerUnfollower=dashboard.getFollowerUnfollower();
        final String[] months = new String[followerUnfollower.getDates().size()];

        for (int i=0;i<followerUnfollower.getDates().size();i++){
            months[i]= String.valueOf(followerUnfollower.getDates().get(i));
            entries.add(new Entry(i, followerUnfollower.getFollowers().get(i)));
            entries1.add(new Entry(i, followerUnfollower.getUnfollowers().get(i)));

        }


        LineDataSet dataSet = new LineDataSet(entries, "Followers");
        dataSet.setColor(ContextCompat.getColor(c, R.color.DeepSkyBlue));
        dataSet.setValueTextColor(ContextCompat.getColor(c, R.color.White));

        LineDataSet dataSet1 = new LineDataSet(entries1, "Unfollowers");
        dataSet1.setColor(ContextCompat.getColor(c, R.color.Red));
        dataSet1.setValueTextColor(ContextCompat.getColor(c, R.color.White));




        chart.setDescription(description);

        //****
        // Controlling X axis
        XAxis xAxis = chart.getXAxis();
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Customizing x axis value

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        //***
        // Controlling right side of y axis
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        // Setting Data
        LineData data = new LineData(dataSet,dataSet1);
        chart.setData(data);
        chart.animateX(2500);
        //refresh
        chart.invalidate();


        mydata=aboutMe.getMe();
        tv_followers=(TextView)view.findViewById(R.id.tvfollow);
        tv_following=(TextView)view.findViewById(R.id.tvfollowing);
        tv_tweetsent=(TextView)view.findViewById(R.id.line_tvtweetsent);
        tv_joindate=(TextView)view.findViewById(R.id.line_tvtweetjoined);
        tv_followers.setText( String.valueOf(mydata.getFollowers()));
        tv_following.setText(String.valueOf(mydata.getFollowing()));
        tv_tweetsent.setText(String.valueOf(mydata.getTweets()));
        tv_joindate.setText(String.valueOf(mydata.getAccountAge()));


        stats=dashboard.getStats();
        tv_unfollowers=(TextView)view.findViewById(R.id.tvunf);
        tv_nonfollowback=(TextView)view.findViewById(R.id.tvnonfollowback);
        tv_muted=(TextView)view.findViewById(R.id.line_tvmuted);
        tv_idontfollowback=(TextView)view.findViewById(R.id.line_tvidontfollowback);
        tv_blocked=(TextView)view.findViewById(R.id.line_tvblocked);

        tv_blocked.setText(String.valueOf(stats.getNewBlocked()));
        tv_idontfollowback.setText(String.valueOf(stats.getNewIDontFollowBack()));
        tv_muted.setText(String.valueOf(stats.getNewMuted()));
        tv_nonfollowback.setText(String.valueOf(stats.getNewNonFollowers()));
        tv_unfollowers.setText(String.valueOf(stats.getUnfollowersCount()));

        tv_new_followers=(TextView)view.findViewById(R.id.tvnewfollow);
        tv_new_following=(TextView)view.findViewById(R.id.tvnewfollowing);
        tv_new_unfollowers=(TextView)view.findViewById(R.id.tvnewunf);
        tv_new_followers.setText(String.valueOf(stats.getNewFollowers()));
        tv_new_following.setText(String.valueOf(stats.getNewFollowing()));
        tv_new_unfollowers.setText(String.valueOf(stats.getNewUnfollowers()));


        tweetAnalyze=dashboard.getTweetAnalyze();
        analyz_tv_average=(TextView)view.findViewById(R.id.analyz_ave);
        analyz_tv_likes=(TextView)view.findViewById(R.id.analyz_like);
        analyz_tv_retweet=(TextView)view.findViewById(R.id.analyz_rt);
        analyz_tv_average.setText(String.valueOf(tweetAnalyze.getPerDay()));
        analyz_tv_likes.setText(String.valueOf(tweetAnalyze.getLikes()));
        analyz_tv_retweet.setText(String.valueOf(tweetAnalyze.getRetweets()));


        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.rc);

        deviceStats=dashboard.getDeviceStats();
        dailyStats=dashboard.getDailyStats();
        getDeviceStats(deviceStats);
        getDailyStats(dailyStats);




        }
    private void getDeviceStats(DeviceStats deviceStats) {
       pieChart.setUsePercentValues(true);

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        for (int i =0;i<deviceStats.getCounts().size();i++){
            yvalues.add(new PieEntry(deviceStats.getCounts().get(i), String.valueOf(deviceStats.getDeviceNames().get(i)+": "+deviceStats.getCounts().get(i)), i));


        }

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setDrawValues(false);

        PieData data = new PieData(dataSet);


        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        Description description = new Description();
        description.setText(getString(R.string.pie_chart));
        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(23f);
        data.setValueTextColor(Color.DKGRAY);
    }

    private void getDailyStats(DailyStats deviceStats) {
        daily_pieChart.setUsePercentValues(true);

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        for (int i =0;i<deviceStats.getCounts().size();i++){
            yvalues.add(new PieEntry(deviceStats.getCounts().get(i), String.valueOf(deviceStats.getNames().get(i)+": "+deviceStats.getCounts().get(i)), i));


        }

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setDrawValues(false);

        PieData data = new PieData(dataSet);


        data.setValueFormatter(new PercentFormatter());
        daily_pieChart.setData(data);
        Description description = new Description();
        description.setText(getString(R.string.pie_chart));
        daily_pieChart.setDescription(description);
        daily_pieChart.setDrawHoleEnabled(true);
        daily_pieChart.setTransparentCircleRadius(58f);
        daily_pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(23f);
        data.setValueTextColor(Color.DKGRAY);
    }
    private ArrayList getData(BestFriends bestFriends){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0;i<bestFriends.getMentionCounts().size();i++){
            entries.add(new BarEntry(i, bestFriends.getMentionCounts().get(i)));

        }
        return entries;
    }
    private ArrayList getData_mm(MostMentions bestFriends){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0;i<bestFriends.getMentionCounts().size();i++){
            entries.add(new BarEntry(i, bestFriends.getMentionCounts().get(i)));

        }
        return entries;
    }
    }





