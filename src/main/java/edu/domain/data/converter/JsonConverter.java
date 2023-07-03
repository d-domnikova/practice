package edu.domain.data.converter;

import edu.domain.data.Task;

import java.util.List;

public interface JsonConverter {
    String toJson (List<Task> tasks);
    List<Task> fromJson (String tasks);
}
