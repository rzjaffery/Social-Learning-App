package com.rzjaffery.sociallearningapplication.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.rzjaffery.sociallearningapplication.data.FirebaseManager;
import com.rzjaffery.sociallearningapplication.model.QuizAttempt;

public class QuizRepository {
    private final DatabaseReference root = FirebaseManager.db().getReference();

    public DatabaseReference attemptRef(String uid){
        return root.child("quizAttempts").child(uid);
    }
    public void saveAttempt(String uid, QuizAttempt attempt){
        String id = root.child("quizAttempts").child(uid).push().getKey();
        attempt.id = id;
        assert id != null;
        attemptRef(uid).child(id).setValue(attempt);
    }
}
