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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TodoItemDAOCollection implements ITodoItemDAO {
    private List<TodoItem> storage = new ArrayList<>();
    private static TodoItemDAOCollection instance;
    private static final Logger logger = Logger.getLogger(TodoItemDAOCollection.class.getName());
    public static void initializeLogger(Logger parentLogger) {
        logger.setLevel(Level.INFO);
        logger.setParent(parentLogger);
    }

    private TodoItemDAOCollection() {
    }

    public static TodoItemDAOCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemDAOCollection();
        }
        return instance;
    }

    public void initializeTodoItem() {
        storage = JSONReader.readToList("todoItem");
    }

    public void shutDownTodoItem() {
        JSONWriter.writeFromListToJson(storage, "todoItem");
    }

    /**
     * Persists a new todoItem to the storage.
     * This method adds a new todoItem to the storage if it does not already exist.
     *
     * @param todoItem The todoItem to persist.
     * @return The newly persisted todoItem.
     * @throws IllegalArgumentException If the provided todoItem is null.
     */
    @Override
    public TodoItem persist(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("Todo item cannot be null.");
        if (todoItem.getId() == 0) {
            todoItem.setId(TodoItemIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(t -> {
                if (t.getId() == todoItem.getId()) {
                    logger.warning("Todo item ID already exists. Changing ID to a unique value.");
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