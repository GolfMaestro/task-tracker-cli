package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task tracker CLI");

        List<Task> taskList1 = new ArrayList<>();

        Tasks taskList = new Tasks(taskList1);

        Scanner scanner = new Scanner(System.in);

        int id = 0;

        boolean exit = false;

        while(!exit) {

            String input;

            input = scanner.nextLine();

            String[] arr = input.split(" ", 2);

            switch (arr[0]) {
                case "add":
                    taskList.addTask(id, arr[1], "in-progress", LocalDateTime.now(), LocalDateTime.now());
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