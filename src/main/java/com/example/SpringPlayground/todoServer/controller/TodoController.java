package com.example.SpringPlayground.todoServer.controller;

import com.example.SpringPlayground.todoServer.domain.Todo;
import com.example.SpringPlayground.todoServer.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    String save(@RequestBody TodoForm todoId) {
        return todoService.save(todoId.getTodoId());
    }

    @GetMapping
    List<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    Todo findOne(@PathVariable("id") String id) {
        return todoService.findOne(id);
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable("id") String id) {
        return todoService.delete(id);
    }

    @DeleteMapping
    void deleteAll() {
        todoService.deleteAll();
    }

    @PatchMapping("/{id}")
    String toggleTodo(@PathVariable("id") String id) {
        return todoService.toggleTodo(id);
    }
}
