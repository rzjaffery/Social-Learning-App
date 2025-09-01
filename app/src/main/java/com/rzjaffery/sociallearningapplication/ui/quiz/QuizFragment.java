// QuizFragment.java
package com.rzjaffery.sociallearningapplication.ui.quiz;

import android.annotation.SuppressLint;
import android.os.*;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.QuestionsData;
import com.rzjaffery.sociallearningapplication.model.Question;

import java.util.List;
import java.util.Map;

public class QuizFragment extends Fragment {
    private TextView questionText, timerText;
    private RadioGroup optionsGroup;
    private Button nextBtn;

    private List<Question> questions;
    private int currentIndex = 0, score = 0;
    private CountDownTimer countDownTimer;
    private String category = "General";

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        questionText = view.findViewById(R.id.questionText);
        optionsGroup = view.findViewById(R.id.optionsGroup);
        nextBtn = view.findViewById(R.id.nextBtn);
        timerText = view.findViewById(R.id.timerText);

        if (getArguments() != null) {
            category = getArguments().getString("category", "General");
        }

        Map<String, List<Question>> allQuestions = QuestionsData.getQuestions();
        questions = allQuestions.get(category);

        loadQuestion();

        nextBtn.setOnClickListener(v -> checkAnswerAndNext());

        return view;
    }

    public static QuizFragment newInstance(String category) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }


    private void loadQuestion() {
        if (currentIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        Question q = questions.get(currentIndex);
        questionText.setText(q.getQuestion());
        optionsGroup.removeAllViews();

        for (int i = 0; i < q.getOptions().size(); i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(q.getOptions().get(i));
            rb.setId(i);
            optionsGroup.addView(rb);
        }

        startTimer();
    }

    private void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();

        countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time: " + (millisUntilFinished / 1000));
            }
            public void onFinish() {
                checkAnswerAndNext(); // auto skip
            }
        }.start();
    }

    private void checkAnswerAndNext() {
        if (countDownTimer != null) countDownTimer.cancel();

        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId == questions.get(currentIndex).getCorrectIndex()) {
            score++;
        }

        currentIndex++;
        loadQuestion();
    }

    private void finishQuiz() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("users")
                .child(uid).child("quizHistory")
                .push().setValue("Category: " + category + ", Score: " + score + "/" + questions.size());

        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        bundle.putInt("total", questions.size());

        Navigation.findNavController(requireView()).navigate(R.id.action_quizFragment_to_resultFragment, bundle);
    }
}
