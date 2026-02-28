package com.vladkonrad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    @Test
    void newItemHasPendingStatus() {
        TodoItem item = new TodoItem(1, "Buy groceries");
        assertEquals(TodoStatus.PENDING, item.getStatus());
    }

    @Test
    void newItemStoresIdAndTitle() {
        TodoItem item = new TodoItem(42, "Write tests");
        assertEquals(42, item.getId());
        assertEquals("Write tests", item.getTitle());
    }

    @Test
    void setStatusChangesStatus() {
        TodoItem item = new TodoItem(1, "Learn Git");
        item.setStatus(TodoStatus.COMPLETED);
        assertEquals(TodoStatus.COMPLETED, item.getStatus());
    }

    @Test
    void setTitleUpdatesTitle() {
        TodoItem item = new TodoItem(1, "Old title");
        item.setTitle("New title");
        assertEquals("New title", item.getTitle());
    }

    @Test
    void toStringContainsIdTitleAndStatus() {
        TodoItem item = new TodoItem(3, "Task three");
        String str = item.toString();
        assertTrue(str.contains("3"));
        assertTrue(str.contains("Task three"));
        assertTrue(str.contains("PENDING"));
    }
}
