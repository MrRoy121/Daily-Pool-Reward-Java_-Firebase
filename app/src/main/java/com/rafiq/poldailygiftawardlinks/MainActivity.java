package com.rafiq.poldailygiftawardlinks;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.LightingColorFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.rafiq.poldailygiftawardlinks.databinding.ActivityMainBinding;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";
    ActivityMainBinding binding;
    //private InterstitialAd interstitial;
    //private AdView adView;
    //private InterstitialAd interstitialAd;

    String rewardLink = "https://mcgam.es/34403Wh";
    CheckChange c = new CheckChange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);

/*

        Facebook..

        interstitialAd = new InterstitialAd(this, "YOUR_PLACEMENT_ID");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {}
            @Override
            public void onInterstitialDismissed(Ad ad) {}
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());}
            @Override
            public void onAdLoaded(Ad ad) {interstitialAd.show();}
            @Override
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}
        };
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
        com.facebook.ads.AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(MainActivity.this, "Error: " + adError.getErrorMessage(), Toast.LENGTH_LONG).show();
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




        binding.flReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardsActivity.class));
                StartAppAd.showAd(MainActivity.this);
            }
        });
        binding.flDailySpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
                StartAppAd.showAd(MainActivity.this);

            }
        });
        binding.flNightGiveAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScratchCardActivity.class));
                StartAppAd.showAd(MainActivity.this);

            }
        });
        binding.flComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartAppAd.showAd(MainActivity.this);
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

/*

Facebook..

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }
*/

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.share:
                shareApp(MainActivity.this);
                break;
            case R.id.group:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/rafiqxd8ballpool")));
                break;
            case R.id.about:
                aboutdev();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void aboutdev(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About Developer");
        builder.setMessage(getString(R.string.dev))
                .setCancelable(false)
                .setPositiveButton("Contact Developer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://m.me/MrRoy121.Androiddev")));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawableResource(R.color.teal_200);
        alert.setTitle("About Developer");
        alert.show();
    }
    public static void shareApp(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        StringBuilder sb = new StringBuilder();
        sb.append("Hi i'm using " + context.getString(R.string.app_name) + " it's amazing " +
                "\n download it form" +
                "\n http://play.google.com/store/apps/details?id=");
        sb.append(context.getPackageName());
        intent.putExtra("android.intent.extra.TEXT", sb.toString());
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onStart() {
        IntentFilter f = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(c,f);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(c);
        super.onStop();
    }
}