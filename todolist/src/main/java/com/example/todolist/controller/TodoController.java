package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//TodoController는 RESTful API를 활용한 컨트롤러로
// POSTMAN 등의 도구와 함께 사용한다.
// 웹에 띄우고싶다면 WebController.

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoService.getTodoById(id);
        return todoItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoItem> deleteTodoItem(@PathVariable Long id) {
        todoService.deleteTodoItem(id);
        return ResponseEntity.noContent().build();
    }

    //웹을 통해 이해해보자
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        Optional<TodoItem> existingTodoItem = todoService.getTodoById(id);
        if (existingTodoItem.isPresent()) {
            todoItem.setId(id);
            return ResponseEntity.ok(todoService.updateTodoItem(todoItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
