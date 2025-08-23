package com.rzjaffery.sociallearningapplication.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rzjaffery.sociallearningapplication.data.FirebaseManager;
import com.rzjaffery.sociallearningapplication.model.User;

import java.util.Objects;

public class UserRepository {
    private final DatabaseReference dbRef = FirebaseManager.db().getReference("users");
    private final FirebaseAuth auth = FirebaseManager.auth();

    public DatabaseReference currentUserRef() {
        return dbRef.child(Objects.requireNonNull(auth.getCurrentUser()).getUid());
    }

    public void saveProfile(User user) {
        dbRef.child(user.uid).setValue(user);
    }

}
