package edu.domain;

import edu.domain.data.Task;
import edu.domain.data.TaskDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppTaskRepositories implements TaskRepositories {
    private final TaskDataSource dataSource;
    private final List<Task> tasks;

    public AppTaskRepositories(TaskDataSource dataSource, List<Task> tasks) {
        this.dataSource = dataSource;
        this.tasks = tasks;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void editTask(Task task, String component, String newValue) {
        switch (component) {
            case "t" -> task.setBody(newValue);
            case "d" -> task.setDeadline(LocalDateTime.parse(newValue,
                    DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
            case "st" -> task.setCompleted(task.isCompleted() == false ? true : false);
        }
    }

    @Override
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        List<Task> search = new ArrayList<>();
        for (Task task: tasks){
            if(task.getBody().toLowerCase().contains(keyword.toLowerCase())){
                search.add(task);
            }
        }
        return search;
    }

    @Override
    public void sort() {
        tasks.sort(Comparator.comparing(Task::getDeadline));
        tasks.sort(Comparator.comparing(Task::isCompleted));
    }

    @Override
    public void saveChanges() {
        dataSource.writeTasks(tasks);
    }
}
