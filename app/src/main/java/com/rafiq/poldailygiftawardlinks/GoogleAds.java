package com.rafiq.poldailygiftawardlinks;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;
/*
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;*/

public class GoogleAds {
   /* private static FrameLayout adContainerView;
    private static AdView adView;

    public static void loadBanner(Activity activity, FrameLayout adContainer) {
        adContainerView = adContainer;
        adView = new AdView(activity);
        adView.setAdUnitId(activity.getString(R.string.adaptive_banner_ad_unit_id));
        adContainerView.addView(adView);
        AdRequest adRequest =
                new AdRequest.Builder()
                        .build();
        AdSize adSize = getAdSize(activity);
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private static AdSize getAdSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }*/
}
