package com.rzjaffery.sociallearningapplication.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.QuizRepository;
import com.rzjaffery.sociallearningapplication.data.repository.TaskRepository;

public class ProfileFragment extends Fragment {
    private TextView tvQuizCnt;
    private TextView tvTaskCnt;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_profile, parent, false);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvEmail = v.findViewById(R.id.tvEmail);
        tvQuizCnt = v.findViewById(R.id.tvQuizCount);
        tvTaskCnt = v.findViewById(R.id.tvTaskCount);

        var user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            tvEmail.setText(user.getEmail());
            tvName.setText(user.getDisplayName() == null ? "User" : user.getDisplayName());
            String uid = user.getUid();

            new QuizRepository().attemptRef(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snap) {
                    tvQuizCnt.setText("Quiz attempts: " + snap.getChildrenCount());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError e) {
                }
            });

            new TaskRepository().tasksRef(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snap) {
                    tvTaskCnt.setText("Tasks: " + snap.getChildrenCount());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError e) {
                }
            });
        } else {
            tvEmail.setText("No email");
            tvName.setText("Guest User");
        }
        return v;
    }

}
