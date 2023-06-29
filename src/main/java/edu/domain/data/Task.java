package edu.domain.data;

import java.time.LocalDate;
public class Task {
    private String body;
    private LocalDate deadline;
    private boolean isCompleted;
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public Task(String body, LocalDate deadline, boolean isCompleted) {
        this.body = body;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task: " + body + "\nDeadline: " + deadline + "\nStatus: " + isCompleted;
    }
}
