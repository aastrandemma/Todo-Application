package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.model.Person;
import org.github.aastrandemma.model.TodoItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemDAOCollectionTest {
    TodoItemDAOCollection testObject;
    TodoItem createdTodoItem;
    TodoItem createdTodoItem2;
    Person createdCreator;

    @BeforeEach
    public void setup() {
        testObject = TodoItemDAOCollection.getInstance();
        createdCreator = new Person("Jane", "Doe", "jane@doe.se");
        createdTodoItem = testObject.persist(new TodoItem("Test Created Title", LocalDate.parse("2024-04-15"), createdCreator));
        createdTodoItem2 = testObject.persist(new TodoItem("Test Created", LocalDate.parse("2024-04-12"), createdCreator));
    }

    @AfterEach
    public void tearDown() {
        testObject = null;
        createdCreator = null;
        createdTodoItem = null;
        createdTodoItem2 = null;
    }

    @Test
    public void testTodoItemInstance() {
        assertNotNull(TodoItemDAOCollection.getInstance());
    }

    @Test
    public void testCreateTodoItem() {
        TodoItem expectedValue = new TodoItem("Create Title", LocalDate.now(), createdCreator);
        TodoItem actualValue = testObject.persist(expectedValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAll() {
        List<TodoItem> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        List<TodoItem> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindById() {
        TodoItem expectedValue = createdTodoItem;
        TodoItem actualValue = testObject.findById(1);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAllByDoneStatusTrue() {
        List<TodoItem> expectedValue = new ArrayList<>();
        createdTodoItem.setDone(true);
        createdTodoItem2.setDone(true);
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        Collection<TodoItem> actualValue = testObject.findAllByDoneStatus(true);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAllByDoneStatusNotTrue() {
        List<TodoItem> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        Collection<TodoItem> actualValue = testObject.findAllByDoneStatus(false);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByTitleContains() {
        List<TodoItem> expectedValue = new ArrayList<>();
        expectedValue.add(testObject.persist(new TodoItem("A new created test title", LocalDate.now(), createdCreator)));
        expectedValue.add(testObject.persist(new TodoItem("A new created test title to test", LocalDate.now(), createdCreator)));
        expectedValue.add(testObject.persist(new TodoItem("A new created test title to test a method", LocalDate.now(), createdCreator)));
        Collection<TodoItem> actualValue = testObject.findByTitleContains("new");

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByPersonId() {
        List<TodoItem> expectedValue = new ArrayList<>();
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        Collection<TodoItem> actualValue = testObject.findByPersonId(createdCreator.getId());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByDeadlineBefore() {
        List<TodoItem> expectedValue = new ArrayList<>();
        createdTodoItem.setDeadline(LocalDate.now().minusDays(5));
        createdTodoItem2.setDeadline(LocalDate.now().minusDays(4));
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        Collection<TodoItem> actualValue = testObject.findByDeadlineBefore(LocalDate.now().plusDays(5));

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByDeadlineAfter() {
        List<TodoItem> expectedValue = new ArrayList<>();
        createdTodoItem.setDeadline(LocalDate.now());
        createdTodoItem2.setDeadline(LocalDate.now());
        expectedValue.add(createdTodoItem);
        expectedValue.add(createdTodoItem2);
        Collection<TodoItem> actualValue = testObject.findByDeadlineAfter(LocalDate.now().minusDays(5));

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemove() {
        testObject.remove(createdTodoItem.getId());

        assertNull(testObject.findById(createdTodoItem.getId()));
    }
}