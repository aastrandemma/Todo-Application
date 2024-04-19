package org.github.aastrandemma.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {
    private Person createdCreator;
    private Person createdAssigned;
    private TodoItem createdTodoItem;
    private TodoItemTask createdTodoItemTask;

    @BeforeEach
    public void setup() {
        createdCreator = new Person("Jane", "Doe", "jane@doe.se");
        createdAssigned = new Person("John", "Doe", "john@doe.se");
        createdTodoItem = new TodoItem("Test title", "Test task description", LocalDate.parse("2024-04-01"), createdCreator);
        createdTodoItemTask = new TodoItemTask(createdTodoItem);
    }

    @AfterEach
    public void tearDown() {
        createdCreator = null;
        createdAssigned = null;
        createdTodoItem = null;
        createdTodoItemTask = null;
    }

    @Test
    public void testCreateTodoItemTask() {
        TodoItemTask actualValue = new TodoItemTask(createdTodoItem);
        assertEquals("Test title", actualValue.getTodoItem().getTitle());
    }

    @Test
    public void testSetAssigned() {
        createdTodoItemTask.setAssignee(createdAssigned);
        assertEquals(createdAssigned.getFirstName(), createdTodoItemTask.getAssignee().getFirstName());
    }

    @Test
    public void testIsAssigneeNotTrue() {
        assertFalse(createdTodoItemTask.isAssigned());
    }

    @Test
    public void testIsAssignee() {
        createdTodoItemTask.setAssignee(createdAssigned);
        assertTrue(createdTodoItemTask.isAssigned());
    }
}