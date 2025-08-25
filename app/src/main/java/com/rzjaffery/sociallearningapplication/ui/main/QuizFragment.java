package com.rzjaffery.sociallearningapplication.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.QuizRepository;
import com.rzjaffery.sociallearningapplication.model.Question;
import com.rzjaffery.sociallearningapplication.model.QuizAttempt;

import java.util.*;

public class QuizFragment extends Fragment {
    private TextView tvQuestion, tvTimer;
    private RadioGroup optionsGroup;
    private Button btnNext, btnHistory;
    private List<Question> questions;
    private int index = 0, score = 0;
    private CountDownTimer timer;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_quiz, parent, false);
        tvQuestion = v.findViewById(R.id.tvQuestion);
        tvTimer = v.findViewById(R.id.tvTimer);
        optionsGroup = v.findViewById(R.id.optionsGroup);
        btnNext = v.findViewById(R.id.btnNext);
        btnHistory = v.findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(view -> Navigation.findNavController(v).navigate(R.id.action_quiz_to_history));
        questions = makeDemoQuestions(); // could be dynamic
        loadQuestion();
        btnNext.setOnClickListener(vw -> submitAndNext());
        return v;
    }

    private void loadQuestion() {
        if (timer != null) timer.cancel();
        if (index >= questions.size()) {
            finishQuiz();
            return;
        }
        Question q = questions.get(index);
        tvQuestion.setText((index + 1) + "/" + questions.size() + ": " + q.text);
        optionsGroup.removeAllViews();
        for (int i = 0; i < q.options.size(); i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(q.options.get(i));
            rb.setId(i);
            optionsGroup.addView(rb);
        }
        startTimer();
    }

    private void startTimer() {
        timer = new CountDownTimer(10_000, 1000) {
            @Override
            public void onTick(long ms) {
                tvTimer.setText("Time: " + (ms / 1000) + "s");
            }

            @Override
            public void onFinish() {
                submitAndNext();
            }
        }.start();
    }

    private void submitAndNext() {
        if (timer != null) timer.cancel();
        Question q = questions.get(index);
        int checked = optionsGroup.getCheckedRadioButtonId();
        if (checked == q.correct) score++;
        index++;
        loadQuestion();
    }

    private void finishQuiz() {
        String uid = FirebaseAuth.getInstance().getUid();
        QuizAttempt attempt = new QuizAttempt(null, score, questions.size(), System.currentTimeMillis());
        new QuizRepository().saveAttempt(uid, attempt);
        Toast.makeText(getContext(), "Score: " + score + "/" + questions.size(), Toast.LENGTH_LONG).show();
        // reset or stay
        index = 0;
        score = 0;
        loadQuestion();
    }

    private List<Question> makeDemoQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("1", "Android is developed by?",
                Arrays.asList("Apple", "Google", "Microsoft", "IBM"), 1));
        list.add(new Question("2", "Firebase Realtime DB is?",
                Arrays.asList("SQL", "NoSQL", "Graph", "File"), 1));
        list.add(new Question("3", "Java is?",
                Arrays.asList("Compiled to bytecode", "Purely interpreted", "Assembly", "Markup"), 0));
        list.add(new Question("4", "AdMob is used for?",
                Arrays.asList("Auth", "Ads", "Analytics", "Storage"), 1));
        list.add(new Question("5", "RecyclerView shows?",
                Arrays.asList("Lists/Grids", "Images only", "DB only", "Network only"), 0));
        return list;
    }
}
