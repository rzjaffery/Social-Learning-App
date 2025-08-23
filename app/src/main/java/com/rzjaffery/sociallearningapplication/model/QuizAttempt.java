package com.rzjaffery.sociallearningapplication.model;

public class QuizAttempt {
    public String id;
    public int score, total;
    public long timestamp;

    public QuizAttempt() {}
    public QuizAttempt(String id, int score, int total, long timestamp) {
        this.id = id;
        this.score = score;
        this.total = total;
        this.timestamp = timestamp;
    }
}
