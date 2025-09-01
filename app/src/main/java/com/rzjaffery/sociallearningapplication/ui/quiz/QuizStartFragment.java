// QuizStartFragment.java
package com.rzjaffery.sociallearningapplication.ui.quiz;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.rzjaffery.sociallearningapplication.R;

public class QuizStartFragment extends Fragment {
    private String selectedCategory = "General";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_start, container, false);

        Spinner categorySpinner = view.findViewById(R.id.spinnerCategory);
        Button startBtn = view.findViewById(R.id.btnStartQuiz);

        String[] categories = {"General", "Science", "Math"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories[position];
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        startBtn.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", selectedCategory);
            Navigation.findNavController(v).navigate(R.id.action_quizStartFragment_to_quizFragment, bundle);
        });

        return view;
    }
}
