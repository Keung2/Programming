package com.example.todolist.controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("todo", new TodoItem());
        return "add";
    }

    @PostMapping("/add")
    public String addTodoItem(@ModelAttribute TodoItem todoItem) {
        todoService.addTodoItem(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TodoItem todoItem = todoService.getTodoById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        model.addAttribute("todo", todoItem);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTodoItem(@PathVariable Long id, @ModelAttribute TodoItem todoItem) {
        todoItem.setId(id);
        todoService.updateTodoItem(todoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable Long id) {
        todoService.deleteTodoItem(id);
        return "redirect:/";
    }
}
