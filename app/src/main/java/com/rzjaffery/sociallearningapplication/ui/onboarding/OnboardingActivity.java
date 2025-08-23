package com.rzjaffery.sociallearningapplication.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.main.MainActivity;
import com.rzjaffery.sociallearningapplication.utils.AdsHelper;

public class OnboardingActivity extends AppCompatActivity implements OnboardingAdapter.FinishListener {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_onboarding);

        ViewPager2 pager = findViewById(R.id.pager);
        pager.setAdapter(new OnboardingAdapter(this, this));

        ViewGroup adContainer = findViewById(R.id.banner_container);
        AdsHelper.loadBanner(this, adContainer, "ca-app-pub-3940256099942544/6300978111"); // test banner
    }

    @Override
    public void onFinish() {
        AdsHelper.showInterstitial(this, "ca-app-pub-3940256099942544/1033173712", () -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
