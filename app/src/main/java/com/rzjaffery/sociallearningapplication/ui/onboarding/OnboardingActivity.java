package com.rzjaffery.sociallearningapplication.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.auth.LoginActivity;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 pager;
    private Button btnNext, btnSkip;
    private InterstitialAd interstitial;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        pager = findViewById(R.id.pager);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        pager.setAdapter(new OnboardingAdapter(this));

        btnSkip.setOnClickListener(v -> finishFlow());
        btnNext.setOnClickListener(v -> {
            int i = pager.getCurrentItem();
            if (i < 2) pager.setCurrentItem(i+1, true);
            else finishFlow();
        });

        // Load interstitial ad (test id)
        InterstitialAd.load(this,
                "ca-app-pub-3940256099942544/1033173712",
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override public void onAdLoaded(InterstitialAd ad) { interstitial = ad; }
                });
    }

    private void finishFlow() {
        if (interstitial != null) interstitial.show(this);

        // Always go to Login after onboarding
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
