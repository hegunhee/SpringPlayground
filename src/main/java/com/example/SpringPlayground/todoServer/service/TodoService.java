package com.example.SpringPlayground.todoServer.service;

import com.example.SpringPlayground.todoServer.domain.Todo;
import com.example.SpringPlayground.todoServer.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public String save(String todoText) {
        validateDuplicateTodo(todoText);
        Todo todo = Todo.createTodo(todoText);
        todoRepository.save(todo);
        return todoText;
    }

    private void validateDuplicateTodo(String todoText) {
        Todo findTodo = todoRepository.findOne(todoText);
        if(findTodo != null) {
            throw new IllegalStateException("이미 존재하는 Todo 입니다.");
        }
    }

    public Todo findOne(String todoId) {
        return todoRepository.findOne(todoId);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional
    public String delete(String todoId) {
        todoRepository.delete(todoId);
        return todoId;
    }

    @Transactional
    public void deleteAll() {
        todoRepository.deleteAll();
    }

    @Transactional
    public String toggleTodo(String todoId) {
        Todo todo = todoRepository.findOne(todoId);
        todo.toggleTodo();
        return todo.getText();
    }
}
