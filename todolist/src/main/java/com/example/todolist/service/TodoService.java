package com.example.todolist.service;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    //전체 조회 메서드이므로 List로 반환
    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    //Optional로 반환하여 없는 Id일 경우 nullpointerexception이 아닌 예외 처리를 할 수 있다.
    public Optional<TodoItem> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    public void deleteTodoItem(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoItem updateTodoItem(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

}
