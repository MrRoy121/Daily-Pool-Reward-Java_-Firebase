package com.rafiq.poldailygiftawardlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

public class CongratsActivity extends AppCompatActivity {


    //private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycongrats);
        getSupportActionBar().hide();

        TextView pt = findViewById(R.id.points);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String value = bundle.getString("val");
            pt.setText(value);
        }



        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StartAppAd rewardedVideo = new StartAppAd(CongratsActivity.this);
                rewardedVideo.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {
                        Intent intent=new Intent(CongratsActivity.this,SpinnerActivity.class);
                        startActivity(intent);
                        finish(); }
                });

                rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                    @Override
                    public void onReceiveAd(Ad ad) {
                        rewardedVideo.showAd();
                    }

                    @Override
                    public void onFailedToReceiveAd(Ad ad) {
                        Toast.makeText(getApplicationContext(), "Can't show rewarded video", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



           /*
           Facebook..

           interstitialAd = new InterstitialAd(CongratsActivity.this, "YOUR_PLACEMENT_ID");
                // Create listeners for the Interstitial Ad

                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {}
                    @Override
                    public void onInterstitialDismissed(Ad ad) {}
                    @Override
                    public void onError(Ad ad, AdError adError) {}
                    @Override
                    public void onAdLoaded(Ad ad) {interstitialAd.show();}
                    @Override
                    public void onAdClicked(Ad ad) {}
                    @Override
                    public void onLoggingImpression(Ad ad) {}
                };
                interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
*/

    }
    public void displayInterstitial()
    {
       /* // If Interstitial Ads are loaded then show else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }*/
    }
}