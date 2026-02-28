package com.vladkonrad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTodoRepositoryTest {

    private InMemoryTodoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTodoRepository();
    }

    @Test
    void createReturnsItemWithAutoIncrementedId() {
        TodoItem first = repository.create("First");
        TodoItem second = repository.create("Second");
        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
    }

    @Test
    void findByIdReturnsCorrectItem() {
        repository.create("Task A");
        TodoItem taskB = repository.create("Task B");

        Optional<TodoItem> found = repository.findById(taskB.getId());
        assertTrue(found.isPresent());
        assertEquals("Task B", found.get().getTitle());
    }

    @Test
    void findByIdReturnsEmptyForMissingId() {
        Optional<TodoItem> result = repository.findById(999);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllReturnsAllItems() {
        repository.create("Alpha");
        repository.create("Beta");
        repository.create("Gamma");

        List<TodoItem> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void deleteRemovesItem() {
        TodoItem item = repository.create("To be deleted");
        boolean deleted = repository.delete(item.getId());

        assertTrue(deleted);
        assertTrue(repository.findById(item.getId()).isEmpty());
    }

    @Test
    void deleteReturnsFalseForMissingId() {
        boolean result = repository.delete(404);
        assertFalse(result);
    }

    @Test
    void saveUpdatesExistingItem() {
        TodoItem item = repository.create("Original");
        item.setTitle("Updated");
        repository.save(item);

        Optional<TodoItem> found = repository.findById(item.getId());
        assertTrue(found.isPresent());
        assertEquals("Updated", found.get().getTitle());
    }
}
