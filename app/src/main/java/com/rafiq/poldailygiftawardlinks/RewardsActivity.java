package com.rafiq.poldailygiftawardlinks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;


import com.google.firebase.firestore.Query;
import com.rafiq.poldailygiftawardlinks.databinding.ActivityRewardsBinding;/*
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;*/
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RewardsActivity extends AppCompatActivity {
    ActivityRewardsBinding binding;
    FirebaseFirestore database;
    RewardAdapter adapter;
    //private AdView adView;

    private static final String TAG = "RewardsActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseFirestore.getInstance();
        binding.rewardsRecyclerView.setLayoutManager(new GridLayoutManager(this,
                1));


       /*
       Facebook..

       adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(RewardsActivity.this, "Error: " + adError.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onAdLoaded(Ad ad) {}
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());*/

        database.collection("rewards").orderBy("rewardDate", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //dialog.dismiss();
                if (task.isSuccessful()) {
                    ArrayList<RewardsModel> rewardsList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        RewardsModel rewardsModel = new RewardsModel(document.get("rewardLink", String.class), document.getDate("rewardDate"));

                        rewardsList.add(rewardsModel);
                    }
                    adapter = new RewardAdapter(RewardsActivity.this, rewardsList);
                    binding.rewardsRecyclerView.setAdapter(adapter);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //dialog.dismiss();
                Toast.makeText(RewardsActivity.this, "Try Again Later", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
*/

}