package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.JSONReader;
import org.github.aastrandemma.JSONWriter;
import org.github.aastrandemma.data.ITodoItemDAO;
import org.github.aastrandemma.data.sequencer.TodoItemIdSequencer;
import org.github.aastrandemma.model.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemDAOCollection implements ITodoItemDAO {
    private List<TodoItem> storage = new ArrayList<>();
    private static TodoItemDAOCollection instance;

    private TodoItemDAOCollection() {
    }

    public static TodoItemDAOCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemDAOCollection();
        }
        return instance;
    }

    public void initializeTodoItem() {
        storage = JSONReader.getInstance().readToList("todoItem");
    }

    public void shutDownTodoItem() {
        JSONWriter.getInstance().writeFromListToJson(storage, "todoItem");
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("TodoItem can't be null.");
        if (todoItem.getId() == 0) {
            todoItem.setId(TodoItemIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(t -> {
                if (t.getId() == todoItem.getId()) {
                    System.out.println("TodoItem ID already exist");
                    System.out.println("Changing ID to unique");
                    todoItem.setId(TodoItemIdSequencer.getInstance().nextId());
                }
            });
        }
        storage.add(todoItem);
        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public TodoItem findById(int id) {
        return storage.stream()
                .filter(todoItem -> todoItem.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return storage.stream()
                .filter(todoItem -> todoItem.isDone() == done)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return storage.stream()
                .filter(todoItem -> todoItem.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return storage.stream()
                .filter(todoItem -> todoItem.getCreator().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return storage.stream()
                .filter(todoItem -> todoItem.getDeadline().isBefore(date))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return storage.stream()
                .filter(todoItem -> todoItem.getDeadline().isAfter(date))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        storage.removeIf(todoItem -> todoItem.getId() == id);
    }
}