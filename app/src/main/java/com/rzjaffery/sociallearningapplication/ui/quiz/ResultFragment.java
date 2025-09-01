package com.rzjaffery.sociallearningapplication.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.rzjaffery.sociallearningapplication.R;

public class ResultFragment extends Fragment {
    private static final String ARG_SCORE = "score";
    private static final String ARG_TOTAL = "total";
    private static final String ARG_CATEGORY = "category"; // optional if you support categories

    public static ResultFragment newInstance(int score, int total, String category) {
        ResultFragment frag = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_TOTAL, total);
        args.putString(ARG_CATEGORY, category);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);

        TextView tvFinalScore = v.findViewById(R.id.resultText);
        Button btnRestart = v.findViewById(R.id.btnRestart);
        Button btnBackMain = v.findViewById(R.id.btnBackMain);

        Bundle args = getArguments();
        int score = args != null ? args.getInt(ARG_SCORE, 0) : 0;
        int total = args != null ? args.getInt(ARG_TOTAL, 0) : 0;
        String category = args != null ? args.getString(ARG_CATEGORY, "General") : "General";

        tvFinalScore.setText("Your Score: " + score + "/" + total);

        btnRestart.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", category);
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_resultFragment_to_quizFragment, bundle);
        });


        btnBackMain.setOnClickListener(view -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return v;
    }

}
