package com.rzjaffery.sociallearningapplication.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.rzjaffery.sociallearningapplication.data.FirebaseManager;
import com.rzjaffery.sociallearningapplication.model.ChatMessage;

public class ChatRepository {
    private final DatabaseReference root = FirebaseManager.db().getReference("groupChat");

    public DatabaseReference messageRef(){
        return root;
    }
    public void sendMsg(ChatMessage msg){
        String id = root.push().getKey();
        msg.id = id;
        assert id != null;
        root.child(id).setValue(msg);
    }
}
