package com.vladkonrad;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTodoRepository implements TodoRepository {

    private final Map<Integer, TodoItem> store = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public TodoItem save(TodoItem item) {
        store.put(item.getId(), item);
        return item;
    }

    public TodoItem create(String title) {
        TodoItem item = new TodoItem(nextId++, title);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Optional<TodoItem> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean delete(int id) {
        return store.remove(id) != null;
    }
}
