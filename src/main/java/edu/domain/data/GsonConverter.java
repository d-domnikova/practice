package edu.domain.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonConverter implements JsonConverter{
    private final Gson gson;
    public GsonConverter (Gson gson){
        this.gson = gson;
    }
    @Override
    public String toJson(List<Task> tasks) {
        return gson.toJson(tasks);
    }

    @Override
    public List<Task> fromJson(String tasks) {
        TypeToken<List<Task>> taskType = new TypeToken<>(){};
        return gson.fromJson(tasks, taskType);
    }
}
