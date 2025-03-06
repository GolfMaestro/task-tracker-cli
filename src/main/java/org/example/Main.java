package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task tracker CLI");

        List<Task> taskList1 = new ArrayList<>();

        Tasks taskList = new Tasks(taskList1);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        JSONFile jsonFile = new JSONFile();

        jsonFile.readJSON();

        List<HashMap<String, String>> content = new ArrayList<>();

        //taskList.addTask();

        content = jsonFile.readJSON();

        taskList.hashmapToList(content);

        int id;
        if (!taskList.taskList.isEmpty()) {
            id = taskList.taskList.getLast().getId() + 1;
        }
        else {
            id = 0;
        }

        /////jsonFile.writeJSON();

        //HashMap<String, String> task1 = content.get(0);
        //System.out.println(task1.get(1).get("description"));
        //System.out.println(content.get(0).get("id"));
        //Task testTask = taskList.createTaskObject(task1.get(0).get("id"), task1.get(1).get("description"), task1.get(2).get("status"), task1.get(3).get("createdAt"), task1.get(4).get("updatedAt"));

        //testTask.showTask();

        while(!exit) {

            String input;

            input = scanner.nextLine();

            String[] arr = input.split(" ", 2);

            switch (arr[0]) {
                case "add":
                    taskList.addTask(id, arr[1], "in-progress", String.valueOf(LocalDateTime.now()), String.valueOf(LocalDateTime.now()));
                    jsonFile.writeJSON(taskList.taskList);
                    id++;
                    break;
                case "list":
                    taskList.showTasks();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Oops. Something went wrong");
            }


        }

    }

}