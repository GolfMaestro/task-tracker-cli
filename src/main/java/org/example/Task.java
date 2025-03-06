package org.example;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String description;
    private String status;
    private String CreatedAt;
    private String UpdatedAt;

    public Task(int id, String description, String status, String CreatedAt, String UpdatedAt) {
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

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public void setUpdatedAt(String UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }


}
