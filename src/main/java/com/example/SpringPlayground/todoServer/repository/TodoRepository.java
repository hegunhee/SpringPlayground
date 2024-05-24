package com.example.SpringPlayground.todoServer.repository;

import com.example.SpringPlayground.todoServer.domain.Todo;

import java.util.List;

public interface TodoRepository {

    public List<Todo> findAll();

    public Todo findOne(String id);

    public String save(Todo todo);

    public void delete(String todoText);

    public void deleteAll();
}
