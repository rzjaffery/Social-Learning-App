package com.rzjaffery.sociallearningapplication.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseManager {
    private static FirebaseAuth auth;
    private static FirebaseDatabase db;
    private static FirebaseStorage storage;

    public static FirebaseAuth auth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static FirebaseDatabase db() {
        if (db == null) {
            db = FirebaseDatabase.getInstance();
            db.setPersistenceEnabled(true);
        }
        return db;
    }

    public static FirebaseStorage storage() {
        if (storage == null) {
            storage = FirebaseStorage.getInstance();
            storage.setMaxUploadRetryTimeMillis(10000);
        }
        return storage;
    }
}
