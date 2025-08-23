package com.rzjaffery.sociallearningapplication.model;

public class ChatMessage {
    public String id, uid, name, text;
    public long timestamp;

    public ChatMessage() {
    }

    public ChatMessage(String id, String uid, String name, String text, long timestamp) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.text = text;
        this.timestamp = timestamp;
    }
}
