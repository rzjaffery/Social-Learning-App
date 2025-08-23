package com.rzjaffery.sociallearningapplication.model;

public class User {
    public String uid, name, email, photo;
    public long createdAt;

    public User() {
    }

    public User(String uid, String name, String email, String photo, long createdAt) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.createdAt = createdAt;
    }

}
