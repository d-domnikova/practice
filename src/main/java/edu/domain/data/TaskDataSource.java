package edu.domain.data;

import edu.domain.data.converter.JsonConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TaskDataSource {
    private final JsonConverter jsonConverter;
    private final Path path = Paths.get("src/Tasks.json");;

    public TaskDataSource(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public List<Task> readTasks() throws IOException {
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        String jsonTasks = Files.readString(path);
        return jsonConverter.fromJson(jsonTasks);
    }

    public void writeTasks(List<Task> tasks) {
        String jsonTasks = jsonConverter.toJson(tasks);
        try {
            Files.writeString(path, jsonTasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
