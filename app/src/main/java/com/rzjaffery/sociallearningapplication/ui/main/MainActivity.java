package com.rzjaffery.sociallearningapplication.ui.main;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rzjaffery.sociallearningapplication.R;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_nav_host);
        NavController navController = host.getNavController();
        BottomNavigationView bottom = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottom, navController);
    }
}
