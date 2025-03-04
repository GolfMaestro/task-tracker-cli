package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tasks {

    List<Task> taskList = new ArrayList<>();

    public Tasks(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(int id, String description, String status, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        Task task1 = new Task(id, description, status, CreatedAt, UpdatedAt);
        taskList.add(task1);
    }

    public void showTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).showTask();
        }
    }

}
