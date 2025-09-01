package com.rzjaffery.sociallearningapplication.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.core.AuthPrefs;
import com.rzjaffery.sociallearningapplication.ui.main.MainActivity;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPass;
    private ProgressBar progress;
    private FirebaseAuth auth;
    private CheckBox cbKeepLogged;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup c, @Nullable Bundle b) {
        View v = inflater.inflate(R.layout.fragment_login, c, false);
        etEmail = v.findViewById(R.id.email);
        etPass  = v.findViewById(R.id.password);
        cbKeepLogged = v.findViewById(R.id.cbKeepLogged);
        Button btnLogin = v.findViewById(R.id.btnLogin);
        TextView tvGotoRegister = v.findViewById(R.id.tvGotoRegister);
        progress = v.findViewById(R.id.progress);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(vw -> {
            String email = etEmail.getText().toString().trim();
            String pass  = etPass.getText().toString();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(getContext(), "Enter email & password", Toast.LENGTH_SHORT).show();
                return;
            }
            progress.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        progress.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Save "keep me logged in" choice
                            AuthPrefs.setKeepLoggedIn(requireContext(), cbKeepLogged.isChecked());
                            startActivity(new Intent(requireContext(), MainActivity.class));
                            requireActivity().finish();
                        } else {
                            Toast.makeText(getContext(),
                                    String.valueOf(task.getException()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        tvGotoRegister.setOnClickListener(vw ->
                startActivity(new Intent(requireContext(), RegisterActivity.class)));

        return v;
    }
}
