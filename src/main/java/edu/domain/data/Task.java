package edu.domain.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Task {
    private String body;
    private LocalDateTime deadline;
    private boolean isCompleted;

    public Task(String body, LocalDateTime deadline, boolean isCompleted) {
        this.body = body;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public String getBody() {
        return body;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task: " + body
                + "\nDeadline: " + deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + "\nStatus: " + isCompleted + "\n";
    }
}


