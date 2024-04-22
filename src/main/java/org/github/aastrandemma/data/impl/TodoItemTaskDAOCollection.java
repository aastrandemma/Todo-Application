package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.JSONReader;
import org.github.aastrandemma.JSONWriter;
import org.github.aastrandemma.data.ITodoItemTaskDAO;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;
import org.github.aastrandemma.model.TodoItemTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {
    private List<TodoItemTask> storage = new ArrayList<>();
    private static TodoItemTaskDAOCollection instance;
    private static final Logger logger = Logger.getLogger(TodoItemTaskDAOCollection.class.getName());
    public static void initializeLogger(Logger parentLogger) {
        logger.setLevel(Level.INFO);
        logger.setParent(parentLogger);
    }

    private TodoItemTaskDAOCollection() {
    }

    public static TodoItemTaskDAOCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemTaskDAOCollection();
        }
        return instance;
    }

    public void initializeTodoItemTask() {
        storage = JSONReader.readToList("todoItemTasks");
    }

    public void shutDownTodoItemTask() {
        JSONWriter.writeFromListToJson(storage, "todoItemTasks");
    }

    /**
     * Persists a new todoItemTask to the storage.
     * This method adds a new todoItemTask to the storage if it does not already exist.
     *
     * @param todoItemTask The todoItemTask to persist.
     * @return The newly persisted todoItemTask.
     * @throws IllegalArgumentException If the provided todoItemTask is null.
     */
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("TodoItemTask cannot be null.");
        if (todoItemTask.getId() == 0) {
            todoItemTask.setId(TodoItemTaskIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(t -> {
                if (t.getId() == todoItemTask.getId()) {
                    logger.warning("Todo item task ID already exists. Changing ID to a unique value.");
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