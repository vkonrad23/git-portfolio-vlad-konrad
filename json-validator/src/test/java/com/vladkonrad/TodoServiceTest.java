package com.vladkonrad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    void setUp() {
        service = new TodoService(new InMemoryTodoRepository());
    }

    @Test
    void addTodoCreatesItemWithPendingStatus() {
        TodoItem item = service.addTodo("Read a book");
        assertNotNull(item);
        assertEquals("Read a book", item.getTitle());
        assertEquals(TodoStatus.PENDING, item.getStatus());
    }

    @Test
    void addTodoThrowsForBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTodo(""));
        assertThrows(IllegalArgumentException.class, () -> service.addTodo("   "));
        assertThrows(IllegalArgumentException.class, () -> service.addTodo(null));
    }

    @Test
    void completeTodoMarkesItemAsCompleted() {
        TodoItem item = service.addTodo("Exercise");
        boolean result = service.completeTodo(item.getId());

        assertTrue(result);
        assertEquals(TodoStatus.COMPLETED, item.getStatus());
    }

    @Test
    void completeTodoReturnsFalseForMissingId() {
        boolean result = service.completeTodo(9999);
        assertFalse(result);
    }

    @Test
    void deleteTodoRemovesItem() {
        TodoItem item = service.addTodo("Temporary task");
        boolean deleted = service.deleteTodo(item.getId());

        assertTrue(deleted);
        List<TodoItem> all = service.listAll();
        assertFalse(all.stream().anyMatch(t -> t.getId() == item.getId()));
    }

    @Test
    void listAllReturnsAllItems() {
        service.addTodo("Task 1");
        service.addTodo("Task 2");
        service.addTodo("Task 3");

        assertEquals(3, service.listAll().size());
    }
}
