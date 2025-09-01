package com.rzjaffery.sociallearningapplication.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rzjaffery.sociallearningapplication.model.UserProfile;

public class UserRepository {
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference userRef(String uid){
        return root.child("users").child(uid).child("profile");
    }
    public void save(UserProfile p){
        userRef(p.uid).setValue(p);
    }
}
