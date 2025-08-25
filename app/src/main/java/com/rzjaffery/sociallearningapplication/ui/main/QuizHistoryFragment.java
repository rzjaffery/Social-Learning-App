package com.rzjaffery.sociallearningapplication.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.QuizRepository;
import com.rzjaffery.sociallearningapplication.model.QuizAttempt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizHistoryFragment extends Fragment {
    private List<String> rows = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_quiz_history, parent, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView list = v.findViewById(R.id.list);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, rows);
        list.setAdapter(adapter);
        String uid = FirebaseAuth.getInstance().getUid();
        DatabaseReference ref = new QuizRepository().attemptRef(uid);
        ref.orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                rows.clear();
                for (DataSnapshot s : snap.getChildren()) {
                    QuizAttempt a = s.getValue(QuizAttempt.class);
                    rows.add(new Date(a.timestamp) + "  " + a.score + "/" + a.total);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError e) {
            }
        });
        return v;
    }
}
