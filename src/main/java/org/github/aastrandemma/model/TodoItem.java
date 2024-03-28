package org.github.aastrandemma.model;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.hash;

public class TodoItem {
    private int id; // is an int representing each TodoItem object
    private String title; // Not NULL or empty
    private String taskDescription;
    private LocalDate deadline; // Not NULL
    private boolean done;
    private Person creator; // Represent who created this task

    // Constructor: only with fields that can't be NULL or empty
    public TodoItem(String title, LocalDate deadline, Person creator) {
        setTitle(title);
        setDeadline(deadline);
        setCreator(creator);
    }

    // Constructor: only with all fields that can be defined in initializing
    public TodoItem(String title, String taskDescription, LocalDate deadline, Person creator) {
        this(title, deadline, creator);
        setTaskDescription(taskDescription);
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be NULL or empty.");
        }
        this.title = title;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDeadline(LocalDate deadline) {
        Objects.requireNonNull(deadline, "Deadline can't be null.");
        this.deadline = deadline;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // When creating an item it's always someone who create the item = not NULL
    public void setCreator(Person creator) {
        Objects.requireNonNull(creator, "Creator can't be null.");
        this.creator = creator;
    }

    private int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Person getCreator() {
        return creator;
    }

    public boolean isDone() {
        return done;
    }

    // TodoItem is overdue if currentDate > deadLine
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline);
    }

    @Override
    public int hashCode() {
        return hash(id,title,taskDescription,deadline,done);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TodoItem)) return false;
        return id == ((TodoItem) obj).getId() && Objects.equals(title, ((TodoItem) obj).getTitle()) && Objects.equals(taskDescription, ((TodoItem) obj).getTaskDescription())
                && deadline == ((TodoItem) obj).getDeadline() && done == ((TodoItem) obj).isDone();
    }

    @Override
    public String toString() {
        return "TodoItemInfo {id: " + getId() + ", title: " + getTitle() + ", taskDescription: " + getTaskDescription()
                + ", deadline: " + getDeadline() + ", done: " + isDone() + "}";
    }
}