package com.example.SpringPlayground.todoServer.repository;

import com.example.SpringPlayground.todoServer.domain.Todo;

import java.util.List;

public interface TodoRepository {

    public List<Todo> findAll();

    public Todo findOne(String id);

    public void save(Todo todo);

    public void delete(Todo todo);

    public void deleteAll();
}
