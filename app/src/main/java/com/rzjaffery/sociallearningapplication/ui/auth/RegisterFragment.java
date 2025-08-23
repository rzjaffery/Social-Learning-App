package com.rzjaffery.sociallearningapplication.ui.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.UserRepository;
import com.rzjaffery.sociallearningapplication.model.User;
import com.rzjaffery.sociallearningapplication.ui.onboarding.OnboardingActivity;

public class RegisterFragment extends Fragment {
    private EditText name, email, password;
    private ProgressBar progress;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_register, parent, false);
        name = v.findViewById(R.id.etName);
        email = v.findViewById(R.id.etEmail);
        password = v.findViewById(R.id.etPassword);
        progress = v.findViewById(R.id.progress);
        v.findViewById(R.id.btnRegister).setOnClickListener(vw -> doRegister());
        return v;
    }

    private void doRegister() {
        String n = name.getText().toString().trim(), e = email.getText().toString().trim(), p = password.getText().toString();
        if (TextUtils.isEmpty(n) || TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            toast("Fill all fields");
            return;
        }
        progress.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                    progress.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();
                        new UserRepository().saveProfile(new User(uid, n, e, "", System.currentTimeMillis()));
                        startActivity(new Intent(getActivity(), OnboardingActivity.class));
                        requireActivity().finish();
                    } else
                        toast(task.getException() != null ? task.getException().getMessage() : "Register failed");
                });
    }

    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
