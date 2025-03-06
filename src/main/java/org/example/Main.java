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

        content = jsonFile.readJSON();

        taskList.hashmapToList(content);

        int id;
        if (!taskList.taskList.isEmpty()) {
            id = taskList.taskList.getLast().getId() + 1;
        }
        else {
            id = 0;
        }

        while(!exit) {

            String input;

            input = scanner.nextLine();

            String[] arr = input.split(" ", 2);

            switch (arr[0]) {
                case "add":
                    taskList.addTask(id, arr[1], "todo", String.valueOf(LocalDateTime.now()), String.valueOf(LocalDateTime.now()));
                    jsonFile.writeJSON(taskList.taskList);
                    id++;
                    break;
                case "list":
                    if (arr.length == 1) {
                        taskList.showTasks();
                    }
                    else if (arr[1].equals("done")) {
                        taskList.showTasksDone();
                    }
                    else if (arr[1].equals("todo")) {
                        taskList.showTasksTodo();
                    }
                    else if (arr[1].equals("in-progress")) {
                        taskList.showTasksInprogress();
                    }
                    break;
                case "update":
                    String[] updateArr = input.split(" ", 3);
                    int tempId = Integer.parseInt(updateArr[1]);
                    int arrIndex = -1;
                    for (int i = 0; i < taskList.taskList.size(); i++) {
                        if (taskList.taskList.get(i).getId() == tempId) {
                            arrIndex = i;
                            break;
                        }
                    }
                    if (arrIndex != -1) {
                        taskList.taskList.get(arrIndex).setDescription(updateArr[2]);
                        taskList.taskList.get(arrIndex).setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    }
                    else {
                        System.out.println("Wrong task id");
                    }
                    jsonFile.writeJSON(taskList.taskList);
                    break;
                case "delete":
                    int tempId2 = Integer.parseInt(arr[1]);
                    int arrIndex2 = -1;
                    for (int i = 0; i < taskList.taskList.size(); i++) {
                        if (taskList.taskList.get(i).getId() == tempId2) {
                            arrIndex2 = i;
                            break;
                        }
                    }
                    if (arrIndex2 != -1) {
                        taskList.taskList.remove(arrIndex2);
                    }
                    else {
                        System.out.println("Wrong task id");
                    }
                    jsonFile.writeJSON(taskList.taskList);
                    break;
                case "mark-in-progress":
                    int tempId3 = Integer.parseInt(arr[1]);
                    int arrIndex3 = -1;
                    for (int i = 0; i < taskList.taskList.size(); i++) {
                        if (taskList.taskList.get(i).getId() == tempId3) {
                            arrIndex3 = i;
                            break;
                        }
                    }
                    if (arrIndex3 != -1) {
                        taskList.taskList.get(arrIndex3).setStatus("in-progress");
                        taskList.taskList.get(arrIndex3).setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    }
                    else {
                        System.out.println("Wrong task id");
                    }
                    jsonFile.writeJSON(taskList.taskList);
                    break;
                case "mark-done":
                    int tempId4 = Integer.parseInt(arr[1]);
                    int arrIndex4 = -1;
                    for (int i = 0; i < taskList.taskList.size(); i++) {
                        if (taskList.taskList.get(i).getId() == tempId4) {
                            arrIndex4 = i;
                            break;
                        }
                    }
                    if (arrIndex4 != -1) {
                        taskList.taskList.get(arrIndex4).setStatus("done");
                        taskList.taskList.get(arrIndex4).setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    }
                    else {
                        System.out.println("Wrong task id");
                    }
                    jsonFile.writeJSON(taskList.taskList);
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