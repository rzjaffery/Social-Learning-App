package com.rzjaffery.sociallearningapplication.ui.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.onboarding.OnboardingActivity;

public class LoginFragment extends Fragment {
    private EditText email, password;
    private ProgressBar progress;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_login, parent, false);
        email = v.findViewById(R.id.etEmail);
        password = v.findViewById(R.id.etPassword);
        progress = v.findViewById(R.id.progress);
        v.findViewById(R.id.btnLogin).setOnClickListener(vw -> doLogin());
        v.findViewById(R.id.tvRegister).setOnClickListener(vw ->
                Navigation.findNavController(v).navigate(R.id.action_login_to_register));
        return v;
    }

    private void doLogin() {
        String e = email.getText().toString().trim();
        String p = password.getText().toString();
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            toast("Enter email/password");
            return;
        }
        progress.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                    progress.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getActivity(), OnboardingActivity.class));
                        requireActivity().finish();
                    } else
                        toast(task.getException() != null ? task.getException().getMessage() : "Login failed");
                });
    }

    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
