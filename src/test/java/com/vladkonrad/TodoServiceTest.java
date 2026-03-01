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

    @Test
    void listByStatusFiltersByGivenStatus() {
        service.addTodo("Pending task");
        TodoItem done = service.addTodo("Completed task");
        service.completeTodo(done.getId());

        List<TodoItem> pending = service.listByStatus(TodoStatus.PENDING);
        List<TodoItem> completed = service.listByStatus(TodoStatus.COMPLETED);

        assertEquals(1, pending.size());
        assertEquals(1, completed.size());
        assertEquals(TodoStatus.PENDING, pending.get(0).getStatus());
        assertEquals(TodoStatus.COMPLETED, completed.get(0).getStatus());
    }

    @Test
    void clearCompletedRemovesOnlyCompletedItems() {
        TodoItem first = service.addTodo("Task one");
        TodoItem second = service.addTodo("Task two");
        service.completeTodo(second.getId());

        int removed = service.clearCompleted();

        assertEquals(1, removed);
        List<TodoItem> remaining = service.listAll();
        assertEquals(1, remaining.size());
        assertEquals(first.getId(), remaining.get(0).getId());
    }
}
