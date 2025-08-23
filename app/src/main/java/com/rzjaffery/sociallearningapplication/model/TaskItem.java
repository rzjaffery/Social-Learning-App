package com.rzjaffery.sociallearningapplication.model;

public class TaskItem {
    public String id, title, description, priority;
    public String status;
    public long createdAt;

    public TaskItem() {
    }

    public TaskItem(String id, String title, String description, String priority, String status, long createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
    }
}
