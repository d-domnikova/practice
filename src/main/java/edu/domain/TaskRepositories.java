package edu.domain;

import edu.domain.data.Task;
import java.util.List;

public interface TaskRepositories {
    void addTask (Task task);
    void editTask (Task task);
    void deleteTask (Task task);
    List<Task> searchTasks (String keyWord);
    void sortByBody();
    void sortByDate();
    void saveChanges();
}
