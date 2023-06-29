package edu.domain.data;

import java.util.List;

public interface JsonConverter {
    String toJson (List<Task> tasks);
    List<Task> fromJson (String tasks);
}
