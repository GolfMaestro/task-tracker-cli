package org.example;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tasks {

    List<Task> taskList = new ArrayList<>();

    public Tasks(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(int id, String description, String status, String CreatedAt, String UpdatedAt) {
        Task task1 = new Task(id, description, status, CreatedAt, UpdatedAt);
        taskList.add(task1);
    }

    public void showTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).showTask();
        }
    }

    public Task createTaskObject(String id, String description, String status, String CreatedAt, String UpdatedAt) {
        int id1 = Integer.parseInt(id);

        Task task1 = new Task(id1, description, status, CreatedAt, UpdatedAt);

        return task1;

    }

    public void hashmapToList(List<HashMap<String, String>> hashTasks) {

        for (int i = 0; i < hashTasks.size();i++) {
            HashMap<String, String> temp = hashTasks.get(i);
            Task task1 = new Task(Integer.parseInt(temp.get("id")),
                    temp.get("description"),
                    temp.get("status"),
                    temp.get("createdAt"),
                    temp.get("updatedAt"));
            taskList.add(task1);
        }


    }

}
