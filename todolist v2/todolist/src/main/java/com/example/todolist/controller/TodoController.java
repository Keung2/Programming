package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    private final TodoService service;
    private final TodoRepository todoRepository;

    public TodoController(TodoService service, TodoRepository todoRepository) {
        this.service = service;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<TodoItem> all = todoRepository.findAll();
        model.addAttribute("todos", all);
        model.addAttribute("todoItem", new TodoItem());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute TodoItem todoItem) {
        if (todoItem.getPriority() == 0) {
            todoItem.setPriority(1);
        }
        todoRepository.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, Model model) {
        TodoItem todoItem = todoRepository.findById(id).orElseThrow();
        model.addAttribute("todo", todoItem);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute("todo") TodoItem updatedItem) {
        TodoItem findItem = todoRepository.findById(id).orElseThrow();
        findItem.setTitle(updatedItem.getTitle());
        findItem.setPriority(updatedItem.getPriority());
        todoRepository.save(findItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}
