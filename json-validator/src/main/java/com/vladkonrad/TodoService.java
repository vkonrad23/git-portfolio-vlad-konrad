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
            throw new IllegalArgumentException("Todo title cannot be blank or null.");
        }
        return repository.create(title);
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

    public List<TodoItem> listAll() {
        return repository.findAll();
    }

    public List<TodoItem> listByStatus(TodoStatus status) {
        return repository.findByStatus(status);
    }
}
