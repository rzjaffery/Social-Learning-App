package com.rzjaffery.sociallearningapplication.model;

public class UserProfile {
    public String uid;
    public String name;
    public String email;
    public String avatarUrl;

    public UserProfile() {}
    public UserProfile(String uid, String name, String email, String avatarUrl){
        this.uid = uid; this.name = name; this.email = email; this.avatarUrl = avatarUrl;
    }
}
