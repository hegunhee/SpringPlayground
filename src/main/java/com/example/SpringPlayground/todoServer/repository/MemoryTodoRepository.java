package com.example.SpringPlayground.todoServer.repository;

import com.example.SpringPlayground.todoServer.domain.Todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryTodoRepository implements TodoRepository {

    private static Map<String,Todo> map = new HashMap<>();
    @Override
    public List<Todo> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public Todo findOne(String id) {
        return map.get(id);
    }

    @Override
    public void save(Todo todo) {
        map.put(todo.getText(), todo);
    }

    @Override
    public void delete(Todo todo) {
        map.remove(todo.getText());
    }

    @Override
    public void deleteAll() {
        map.clear();
    }
}
