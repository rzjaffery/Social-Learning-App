package com.rzjaffery.sociallearningapplication.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rzjaffery.sociallearningapplication.core.OnboardingPrefs;
import com.rzjaffery.sociallearningapplication.ui.auth.LoginActivity;
import com.rzjaffery.sociallearningapplication.ui.main.MainActivity;
import com.rzjaffery.sociallearningapplication.ui.onboarding.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!OnboardingPrefs.isDone(this)) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
