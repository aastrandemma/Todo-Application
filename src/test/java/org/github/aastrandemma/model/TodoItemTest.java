package org.github.aastrandemma.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {
    private TodoItem createdTodoItem;
    private Person createdCreator;

    @BeforeEach
    public void setup() {
        createdCreator = new Person("Jane", "Doe", "jane@doe.se");
        createdTodoItem = new TodoItem("Test title", "Test task description", LocalDate.parse("2024-04-01"), createdCreator);
    }

    @AfterEach
    public void tearDown() {
        createdCreator = null;
        createdTodoItem = null;
    }

    @Test
    public void testCreateTodoItem() {
        TodoItem actualValue = new TodoItem("Test Create", "Test Create", LocalDate.parse("2024-04-16"), createdCreator);
        assertEquals("Test Create", actualValue.getTitle());
    }

    @Test
    public void testSetAndGetTitle() {
        createdTodoItem.setTitle("Changing Title");
        assertEquals("Changing Title", createdTodoItem.getTitle());
    }

    @Test
    public void testSetAndGetTaskDescription() {
        createdTodoItem.setTaskDescription("Changing The Task Description");
        assertEquals("Changing The Task Description", createdTodoItem.getTaskDescription());
    }

    @Test
    public void setAndGetCreator() {
        Person changeCreator = new Person("John", "Smith", "john@smith.se");
        createdTodoItem.setCreator(changeCreator);
        assertEquals(changeCreator.getFirstName(), createdTodoItem.getCreator().getFirstName());
    }

    @Test
    public void testSetIsDone() {
        createdTodoItem.setDone(true);
        assertTrue(createdTodoItem.isDone());
    }

    @Test
    public void testIsOverdue() {
        assertTrue(createdTodoItem.isOverdue());
    }

    @Test
    public void testIsNotOverdue() {
        createdTodoItem.setDeadline(LocalDate.now().plusDays(5));
        assertFalse(createdTodoItem.isOverdue());
    }
}