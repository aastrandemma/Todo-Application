package org.github.aastrandemma.model;

import java.util.Objects;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee; // person assigned to the task

    // Constructor: only with fields that can't be NULL
    public TodoItemTask(TodoItem todoItem) {
        setTodoItem(todoItem);
    }

    // Constructor: only with all fields that can be defined in initializing
    public TodoItemTask(TodoItem todoItem, Person assignee) {
        this(todoItem);
        setAssignee(assignee);
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setTodoItem(TodoItem todoItem) {
        Objects.requireNonNull(todoItem, "Todo item can't be null.");
        this.todoItem = todoItem;
    }

    public void setAssignee(Person assignee) {
        if (assignee != null) {
            setAssigned(true);
        }
        this.assignee = assignee;
    }

    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "TodoItemTaskInfo {id: " + getId() + ", assigned: " + isAssigned() + ", todoItem: " + getTodoItem() + ", assignee: not assigned}";
    }
}