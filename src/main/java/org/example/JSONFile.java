package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONFile {

    Path path = Paths.get("Tasks.json");

    public JSONFile() {

    }

    public List<HashMap<String, String>> readJSON() {

        // just read file in this list line by line
        isExist();
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<HashMap<String, String>> tasks = new ArrayList<>();

        boolean istask = false;
        HashMap<String, String> field = new HashMap<>();
        for (int i = 1; i < (content.size() - 1); i++) {

            String s = content.get(i);
            String key = "";
            String value = "";
            boolean pair = false;
            boolean key1 = true;

            // parse every string
            for (int j = 0; j < s.length(); j++) {

                if (s.charAt(j) == '{') {
                    istask = true;
                }
                else if (s.charAt(j) == '}') {
                    istask = false;
                }

                if (pair) {
                    if (key1) {
                        key = key + s.charAt(j);
                    }
                    else {
                        value = value + s.charAt(j);
                    }
                }

                if (s.charAt(j) == '\"') {
                    pair = !pair;
                }
                else if (s.charAt(j) == ':') {
                    key1 = false;
                }
            }
            if (!key.isEmpty() && !value.isEmpty()) {
                key = key.substring(0, key.length() - 1);
                value = value.substring(0, value.length() - 1);
                field.put(key, value);
            }
            if (!istask) {
                tasks.add(new HashMap<String, String>(field));
                field.clear();
            }

        }

        return tasks;

    }

    public void writeJSON(List<Task> tasks) {

        isExist();

        try {
            Files.writeString(path, "[\n");
            for (int i = 0; i < tasks.size(); i++) {
                Files.writeString(path, "{\n", StandardOpenOption.APPEND);
                Files.writeString(path, "\"id\": \"" + tasks.get(i).getId() + "\",\n", StandardOpenOption.APPEND);
                Files.writeString(path, "\"description\": \"" + tasks.get(i).getDescription() + "\",\n", StandardOpenOption.APPEND);
                Files.writeString(path, "\"status\": \"" + tasks.get(i).getStatus() + "\",\n", StandardOpenOption.APPEND);
                Files.writeString(path, "\"createdAt\": \"" + tasks.get(i).getCreatedAt() + "\",\n", StandardOpenOption.APPEND);
                Files.writeString(path, "\"updatedAt\": \"" + tasks.get(i).getUpdatedAt() + "\"\n", StandardOpenOption.APPEND);
                if (i == (tasks.size() - 1)) {
                    Files.writeString(path, "}\n", StandardOpenOption.APPEND);
                }
                else {
                    Files.writeString(path, "},\n", StandardOpenOption.APPEND);
                }
            }
            Files.writeString(path, "]", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void isExist() {
        boolean exist = Files.exists(path);

        if (!exist) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
