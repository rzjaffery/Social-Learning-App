package com.rzjaffery.sociallearningapplication.ui.auth;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.rzjaffery.sociallearningapplication.R;

public class RegisterActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_host);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RegisterFragment())
                    .commit();
        }
    }
}
