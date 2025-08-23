package com.rzjaffery.sociallearningapplication.model;

import java.util.List;

public class Question {
    public String id, text;
    public List<String> options;
    public int correct;

    public Question() {
    }

    public Question(String id, String text, List<String> options, int correct) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correct = correct;
    }
}
