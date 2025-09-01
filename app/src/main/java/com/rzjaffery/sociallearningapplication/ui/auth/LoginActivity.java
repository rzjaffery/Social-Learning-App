package com.rzjaffery.sociallearningapplication.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_simple_host);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LoginFragment())
                    .commit();
        }
    }
}
