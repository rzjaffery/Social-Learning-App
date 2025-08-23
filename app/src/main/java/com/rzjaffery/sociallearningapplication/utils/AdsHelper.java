package com.rzjaffery.sociallearningapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdsHelper {
    public static void loadBanner(Context ctx, ViewGroup container, String unitId) {
        AdView adView = new AdView(ctx);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(unitId);
        container.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    public interface InterstitialListener {
        void onClosed();
    }

    public static void loadInterstitial(Activity activity, String unitId, InterstitialListener listener) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, unitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (listener != null) listener.onClosed();
                    }
                });
                ad.show(activity);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                if (listener != null) listener.onClosed(); // proceed if ad fails
            }
        });
    }
}
