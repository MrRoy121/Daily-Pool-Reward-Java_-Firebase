package com.rafiq.poldailygiftawardlinks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.CategoryViewHolder> {
    Context context;
    ArrayList<RewardsModel> rewardsList;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    //private RewardedVideoAd rewardedVideoAd;
    private final String TAG = "context.class.getSimpleName()";

    public RewardAdapter(Context context, ArrayList<RewardsModel> rewardsList) {
        this.context = context;
        this.rewardsList = rewardsList;

    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reward, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        RewardsModel model = rewardsList.get(position);
        final StartAppAd rewardedVideo = new StartAppAd(context);
        holder.tv_date.setText(dateFormat.format(model.getRewardDate()));

/*
Facebook..
        rewardedVideoAd = new RewardedVideoAd(context, "YOUR_PLACEMENT_ID");
       */

        holder.btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rewardedVideo.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.rewardLink)));
                    }
                });

                rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                    @Override
                    public void onReceiveAd(Ad ad) {
                        rewardedVideo.showAd();
                    }

                    @Override
                    public void onFailedToReceiveAd(Ad ad) {
                        Toast.makeText(context, "Complete The Video To Get Reward!!", Toast.LENGTH_SHORT).show();
                    }
                });


               /*

                Facebook..

                RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
                    @Override
                    public void onError(Ad ad, AdError error) {
                        Log.e(TAG, "Rewarded video ad failed to load: " + error.getErrorMessage());
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        Log.d(TAG, "Rewarded video ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        Log.d(TAG, "Rewarded video ad impression logged!");
                    }
                    @Override
                    public void onRewardedVideoCompleted() {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.rewardLink)));
                    }
                    @Override
                    public void onRewardedVideoClosed() {
                        Toast.makeText(context, "Complete The Video To Get Reward!!", Toast.LENGTH_SHORT).show();
                    }
                };
                rewardedVideoAd.loadAd(rewardedVideoAd.buildLoadAdConfig().withAdListener(rewardedVideoAdListener).build());
                */

            }
        });

    }

    @Override
    public int getItemCount() {
        return rewardsList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date;
        Button btn_open;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            btn_open = itemView.findViewById(R.id.btn_open);
        }
    }


}
