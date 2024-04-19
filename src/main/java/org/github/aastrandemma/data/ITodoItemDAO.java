package org.github.aastrandemma.data;

import org.github.aastrandemma.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

public interface ITodoItemDAO extends IBaseByObjectDAO<TodoItem> {
    TodoItem findById(int id);
    Collection<TodoItem> findAllByDoneStatus(boolean done);
    Collection<TodoItem> findByTitleContains(String title);
    Collection<TodoItem> findByPersonId(int personId);
    Collection<TodoItem> findByDeadlineBefore(LocalDate date);
    Collection<TodoItem> findByDeadlineAfter(LocalDate date);
    void remove(int id);
}