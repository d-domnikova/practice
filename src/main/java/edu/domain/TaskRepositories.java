package edu.domain;

import edu.domain.data.Task;

import java.util.List;

public interface TaskRepositories {
    void addTask (Task task);
    void editTask (Task task, String component, String newValue);
    void deleteTask (int index);
    List<Task> searchTasks (String keyword);
    void sort();
    void saveChanges();
}
