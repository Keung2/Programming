package com.example.todolist.service;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> findAll() {
        return repository.findAll();
    }

    public TodoItem save(TodoItem todoItem) {
        return repository.save(todoItem);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
