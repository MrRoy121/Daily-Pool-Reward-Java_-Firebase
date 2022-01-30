package com.rafiq.poldailygiftawardlinks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anupkumarpanwar.scratchview.ScratchView;

import com.rafiq.poldailygiftawardlinks.databinding.ActivityScratchCardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

public class ScratchCardActivity extends AppCompatActivity {
    private static final String TAG = "ScratchCardActivity";
    ActivityScratchCardBinding binding;

    //private RewardedVideoAd rewardedVideoAd;
    //private AdView adView;
    //private InterstitialAd interstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScratchCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


/*

Facebook..

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_90);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
        com.facebook.ads.AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(ScratchCardActivity.this, "Error: " + adError.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onAdLoaded(Ad ad) {}
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

*/

        ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Scratch Card");
        dialog.show();


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("nightGiveaway").limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String number = document.get("number", String.class);
                        binding.tvNumber.setText(number);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(ScratchCardActivity.this, "Try Again Later", Toast.LENGTH_SHORT).show();
            }
        });


        binding.scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView) {

                binding.scratchView.setVisibility(View.GONE);
                Toast.makeText(ScratchCardActivity.this, "WoW you got it", Toast.LENGTH_SHORT).show();
                StartAppAd.showAd(ScratchCardActivity.this);


/*
Facebook..

                rewardedVideoAd = new RewardedVideoAd(ScratchCardActivity.this, "YOUR_PLACEMENT_ID");
                RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
                    @Override
                    public void onError(Ad ad, AdError error) {
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        rewardedVideoAd.show();
                    }
                    @Override
                    public void onAdClicked(Ad ad) {}
                    @Override
                    public void onLoggingImpression(Ad ad) {}
                    @Override
                    public void onRewardedVideoCompleted() {
                        binding.scratchView.setVisibility(View.GONE);
                        //interstitial.loadAd(adIRequest);
                        Toast.makeText(ScratchCardActivity.this, "WoW you got it", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRewardedVideoClosed() {
                        Toast.makeText(ScratchCardActivity.this, "Complete The Video To Get Reward!!", Toast.LENGTH_SHORT).show();

                    }
                };
                rewardedVideoAd.loadAd(rewardedVideoAd.buildLoadAdConfig().withAdListener(rewardedVideoAdListener).build());
*/

            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {
                if (percent >= 0.5) {
                    Log.d("Reveal Percentage", "onRevealPercentChangedListener: " + String.valueOf(percent));
                }
            }
        });
    }

    public void displayInterstitial()
    {
        /*// If Interstitial Ads are loaded then show else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }*/
    }
/*

Facebook..

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
*/

}