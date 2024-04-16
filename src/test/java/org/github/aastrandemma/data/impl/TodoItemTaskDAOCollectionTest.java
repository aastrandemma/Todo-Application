package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.model.Person;
import org.github.aastrandemma.model.TodoItem;
import org.github.aastrandemma.model.TodoItemTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskDAOCollectionTest {
    TodoItemTaskDAOCollection testObject;
    private TodoItemTask createdTodoItemTask;
    private TodoItem createdTodoItem;
    private Person createdAssigned;
    private Person createdCreator;

    @BeforeEach
    public void setup() {
        testObject = TodoItemTaskDAOCollection.getInstance();
        createdAssigned = new Person("Jane", "Doe", "jane@doe.se");
        createdCreator = new Person("John", "Doe", "john@doe.se");
        createdTodoItem = new TodoItem("Test", LocalDate.now().plusDays(2), createdCreator);
        createdTodoItemTask = testObject.persist(new TodoItemTask(createdTodoItem, createdAssigned));
    }

    @AfterEach
    public void tearDown() {
        testObject = null;
        createdAssigned = null;
        createdCreator = null;
        createdTodoItem = null;
        createdTodoItemTask = null;
    }

    @Test
    public void testTodoItemTaskInstance() {
        assertNotNull(TodoItemTaskDAOCollection.getInstance());
    }

    @Test
    public void testCreateTodoItemTask() {
        TodoItemTask expectedValue = testObject.persist(new TodoItemTask(createdTodoItem, createdAssigned));
        TodoItemTask actualValue = testObject.persist(expectedValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAllTodoItemTasks() {
        List<TodoItemTask> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItemTask);
        List<TodoItemTask> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByAssignedStatus() {
        List<TodoItemTask> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItemTask);
        Collection<TodoItemTask> actualValue = testObject.findByAssignedStatus(true);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByPersonId() {
        List<TodoItemTask> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItemTask);
        expectedValue.add(testObject.persist(new TodoItemTask(createdTodoItem, createdAssigned)));
        expectedValue.add(testObject.persist(new TodoItemTask(createdTodoItem, createdAssigned)));
        expectedValue.add(testObject.persist(new TodoItemTask(createdTodoItem, createdAssigned)));
        Collection<TodoItemTask> actualValue = testObject.findByPersonId(createdAssigned.getId());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemove() {
        testObject.remove(createdTodoItemTask.getId());

        assertNull(testObject.findById(createdTodoItemTask.getId()));
    }
}