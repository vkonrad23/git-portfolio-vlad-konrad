package com.vladkonrad;

public class TodoItem {

    private final int id;
    private String title;
    private TodoStatus status;

    public TodoItem(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = TodoStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, title, status);
    }
}
