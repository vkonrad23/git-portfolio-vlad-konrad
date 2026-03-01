package com.vladkonrad;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    TodoItem save(TodoItem item);

    Optional<TodoItem> findById(int id);

    List<TodoItem> findAll();

    boolean delete(int id);

    List<TodoItem> findByStatus(TodoStatus status);
}
