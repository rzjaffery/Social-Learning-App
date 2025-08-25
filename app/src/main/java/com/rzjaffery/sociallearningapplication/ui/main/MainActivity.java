package com.rzjaffery.sociallearningapplication.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.auth.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_nav_host);
        NavController navController = host.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav,navController);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) {
//            // not logged in → open login screen
//            startActivity(new Intent(this, LoginFragment.class));
//            finish();
//        } else {
//            // already logged in → open main/home screen
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }

    }


}