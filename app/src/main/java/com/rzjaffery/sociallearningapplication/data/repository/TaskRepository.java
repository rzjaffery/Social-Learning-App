package com.rzjaffery.sociallearningapplication.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.rzjaffery.sociallearningapplication.data.FirebaseManager;
import com.rzjaffery.sociallearningapplication.model.TaskItem;

public class TaskRepository {
    private final DatabaseReference root = FirebaseManager.db().getReference();

    public DatabaseReference tasksRef(String uid){
        return root.child("tasks").child(uid);
    }
    public void addOrUpdate(String uid, TaskItem item){
        if (item.id == null || item.id.isEmpty()) item.id = tasksRef(uid).push().getKey();
        tasksRef(uid).child(item.id).setValue(item);
    }
    public void delete(String uid, String id){
        tasksRef(uid).child(id).removeValue();
    }
}
