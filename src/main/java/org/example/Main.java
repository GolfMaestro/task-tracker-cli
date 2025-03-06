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

            int arrIndex = -1;

            switch (arr[0]) {
                case "add":
                    addTask(taskList, jsonFile, id, arr);
                    id++;
                    break;
                case "list":
                    showTasks(taskList, arr);
                    break;
                case "update":
                    updateTask(input, arrIndex, taskList, jsonFile);
                    break;
                case "delete":
                    deleteTask(arrIndex, arr, taskList, jsonFile);
                    break;
                case "mark-in-progress":
                    markInprogressTask(arrIndex, arr, taskList, jsonFile);
                    break;
                case "mark-done":
                    markDoneTask(arrIndex, arr, taskList, jsonFile);
                    break;
                case "exit":
                    exit = true;
                    break;
                case "help":
                    showHelp();
                    break;
                default:
                    System.out.println("Oops. Something went wrong. Enter \"help\" to show commands");
            }


        }

    }

    private static int isIdExist(String[] arr, Tasks taskList) {
        int tempId = Integer.parseInt(arr[1]);
        int arrIndex = -1;
        for (int i = 0; i < taskList.taskList.size(); i++) {
            if (taskList.taskList.get(i).getId() == tempId) {
                arrIndex = i;
                break;
            }
        }
        return  arrIndex;
    }

    private static void addTask(Tasks taskList, JSONFile jsonFile, int id, String[] arr) {
        taskList.addTask(id, arr[1], "todo", String.valueOf(LocalDateTime.now()), String.valueOf(LocalDateTime.now()));
        jsonFile.writeJSON(taskList.taskList);
    }

    private static void showTasks(Tasks taskList, String[] arr) {
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
    }

    private static void updateTask(String input, int arrIndex, Tasks taskList, JSONFile jsonFile) {
        String[] updateArr = input.split(" ", 3);
        arrIndex = isIdExist(updateArr, taskList);
        if (arrIndex != -1) {
            taskList.taskList.get(arrIndex).setDescription(updateArr[2]);
            taskList.taskList.get(arrIndex).setUpdatedAt(String.valueOf(LocalDateTime.now()));
        }
        else {
            System.out.println("Wrong task id");
        }
        jsonFile.writeJSON(taskList.taskList);
    }

    private static void deleteTask(int arrIndex, String[] arr, Tasks taskList, JSONFile jsonFile) {
        arrIndex = isIdExist(arr, taskList);
        if (arrIndex != -1) {
            taskList.taskList.remove(arrIndex);
        }
        else {
            System.out.println("Wrong task id");
        }
        jsonFile.writeJSON(taskList.taskList);
    }

    private static void markInprogressTask(int arrIndex, String[] arr, Tasks taskList, JSONFile jsonFile) {
        arrIndex = isIdExist(arr, taskList);
        if (arrIndex != -1) {
            taskList.taskList.get(arrIndex).setStatus("in-progress");
            taskList.taskList.get(arrIndex).setUpdatedAt(String.valueOf(LocalDateTime.now()));
        }
        else {
            System.out.println("Wrong task id");
        }
        jsonFile.writeJSON(taskList.taskList);
    }

    private static void markDoneTask(int arrIndex, String[] arr, Tasks taskList, JSONFile jsonFile) {
        arrIndex = isIdExist(arr, taskList);
        if (arrIndex != -1) {
            taskList.taskList.get(arrIndex).setStatus("done");
            taskList.taskList.get(arrIndex).setUpdatedAt(String.valueOf(LocalDateTime.now()));
        }
        else {
            System.out.println("Wrong task id");
        }
        jsonFile.writeJSON(taskList.taskList);
    }

    private static void showHelp() {
        System.out.println("help");
        System.out.println("add [description]");
        System.out.println("update [id] [new description]");
        System.out.println("delete [id]");
        System.out.println("list [todo, in-progress, done], leave empty to list all");
        System.out.println("mark-in-progress [id]");
        System.out.println("mark-done [id]");
        System.out.println("exit");
    }
}