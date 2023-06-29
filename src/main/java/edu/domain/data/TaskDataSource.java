package edu.domain.data;

import java.util.List;

public class TaskDataSource {
    private final JsonConverter jsonConverter;

    public TaskDataSource(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public List<Task> writeTasks() {
        return jsonConverter.fromJson("");
    }

    public void writeTasks(List<Task> tasks) {
        String jsonTasks = jsonConverter.toJson(tasks);

    }
}
