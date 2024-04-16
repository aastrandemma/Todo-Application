package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.data.ITodoItemTaskDAO;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;
import org.github.aastrandemma.model.TodoItemTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {
    private final List<TodoItemTask> storage = new ArrayList<>();
    private static TodoItemTaskDAOCollection instance;

    private TodoItemTaskDAOCollection() {
    }

    public static TodoItemTaskDAOCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemTaskDAOCollection();
        }
        return instance;
    }
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("TodoItemTask can't be null.");
        if (todoItemTask.getId() == 0) {
            todoItemTask.setId(TodoItemTaskIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(t -> {
                if (t.getId() == todoItemTask.getId()) {
                    System.out.println("TodoItemTask ID already exist");
                    System.out.println("Changing ID to unique");
                    todoItemTask.setId(TodoItemTaskIdSequencer.getInstance().nextId());
                }
            });
        }
        storage.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public List<TodoItemTask> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public TodoItemTask findById(int id) {
        return storage.stream()
                .filter(todoItemTask -> todoItemTask.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        return storage.stream()
                .filter(todoItemTask -> todoItemTask.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return storage.stream()
                .filter(todoItemTask -> todoItemTask.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        storage.removeIf(todoItemTask -> todoItemTask.getId() == id);
    }
}