package org.github.aastrandemma.data;

import org.github.aastrandemma.model.TodoItemTask;

import java.util.Collection;

public interface ITodoItemTaskDAO extends IBaseByObjectDAO<TodoItemTask> {
    TodoItemTask findById(int id);
    Collection<TodoItemTask> findByAssignedStatus(boolean status);
    Collection<TodoItemTask> findByPersonId(int personId);
    void remove(int id);
}