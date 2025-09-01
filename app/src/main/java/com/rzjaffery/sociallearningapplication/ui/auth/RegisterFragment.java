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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.ui.main.MainActivity;

import java.util.HashMap;

public class RegisterFragment extends Fragment {

    private EditText etName, etEmail, etPass, etAvatar;
    private ProgressBar progress;
    private FirebaseAuth auth;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup c, @Nullable Bundle b) {
        View v = inflater.inflate(R.layout.fragment_register, c, false);
        etName  = v.findViewById(R.id.name);
        etEmail = v.findViewById(R.id.email);
        etPass  = v.findViewById(R.id.password);
        etAvatar= v.findViewById(R.id.avatarUrl);
        Button btn = v.findViewById(R.id.btnRegister);
        progress = v.findViewById(R.id.progress);
        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(vw -> {
            String name = etName.getText().toString().trim();
            String email= etEmail.getText().toString().trim();
            String pass = etPass.getText().toString();
            String avatar = etAvatar.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(getContext(), "Fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }
            progress.setVisibility(View.VISIBLE);
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                progress.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();

                    // Save extra profile data in database
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                    String uid = user.getUid();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("avatar", ""); // default
                    ref.child(uid).setValue(map);

                    Intent intent = new Intent(requireContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    requireActivity().finish(); // prevent going back
                } else {
                    Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return v;
    }
}
