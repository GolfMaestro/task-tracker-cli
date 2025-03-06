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
                    taskList.addTask(id, arr[1], "in-progress", String.valueOf(LocalDateTime.now()), String.valueOf(LocalDateTime.now()));
                    jsonFile.writeJSON(taskList.taskList);
                    id++;
                    break;
                case "list":
                    taskList.showTasks();
                    break;
                case "update":
                    // what if id > 9?
                    String[] updateArr = input.split(" ", 3);
                    System.out.println(updateArr[0]);
                    System.out.println(updateArr[1]);
                    System.out.println(updateArr[2]);
                    int tempId = Integer.parseInt(updateArr[1]);
                    int arrIndex = -1;
                    for (int i = 0; i < taskList.taskList.size(); i++) {
                        if (taskList.taskList.get(i).getId() == tempId) {
                            arrIndex = i;
                            break;
                        }
                    }
                    if ((arrIndex != -1) && (arrIndex < taskList.taskList.size())) {
                        taskList.taskList.get(arrIndex).setDescription(updateArr[2]);
                        taskList.taskList.get(arrIndex).setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    }
                    else {
                        System.out.println("Wrong task id");
                    }
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