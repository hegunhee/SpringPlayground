package com.example.SpringPlayground.todoServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Todo {

    @Id
    private String text;
    private Boolean isCheck;

    protected Todo() {

    }

    public static Todo createTodo(String text) {
        Todo todo = new Todo();
        todo.text = text;
        todo.isCheck = false;
        return todo;
    }

    public void toggleTodo() {
        this.isCheck = !this.isCheck;
    }
}
