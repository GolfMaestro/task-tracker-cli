package org.example;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String description;
    private String status;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Task(int id, String description, String status, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public void showTask() {
        System.out.println("============================================");
        System.out.println("ID: " + id);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Created at: " + CreatedAt);
        System.out.println("Updated at: " + UpdatedAt);
    }

}
