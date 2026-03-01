package com.vladkonrad;

import java.util.List;
import java.util.Optional;

public class TodoService {

    private final InMemoryTodoRepository repository;

    public TodoService(InMemoryTodoRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodo(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required and must not be blank or null.");
        }
        return repository.create(title.strip());
    }

    public boolean completeTodo(int id) {
        Optional<TodoItem> opt = repository.findById(id);
        if (opt.isPresent()) {
            opt.get().setStatus(TodoStatus.COMPLETED);
            repository.save(opt.get());
            return true;
        }
        return false;
    }

    public boolean deleteTodo(int id) {
        return repository.delete(id);
    }

    public int clearCompleted() {
        List<TodoItem> completed = repository.findByStatus(TodoStatus.COMPLETED);
        for (TodoItem item : completed) {
            repository.delete(item.getId());
        }
        return completed.size();
    }

    public List<TodoItem> listAll() {
        return repository.findAll();
    }

    public List<TodoItem> listByStatus(TodoStatus status) {
        return repository.findByStatus(status);
    }
}
