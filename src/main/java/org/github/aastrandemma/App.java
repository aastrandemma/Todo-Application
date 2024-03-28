package org.github.aastrandemma;

import org.github.aastrandemma.model.Person;
import org.github.aastrandemma.model.TodoItem;
import org.github.aastrandemma.model.TodoItemTask;

import java.time.LocalDate;

public class App {
    public static void main( String[] args ) {
        Person person = new Person("Jane","Doe", "jane@doe.com");
        Person person2 = new Person("John", "Doe", "john@doe.com");
        System.out.println(person.getSummary());
        System.out.println();

        TodoItem todoItem = new TodoItem("Work out", LocalDate.parse("2024-03-25"), person); // creator of item
        TodoItem todoItemTwo = new TodoItem("Buy food", "Milk, Eggs, Meat", LocalDate.parse("2024-03-20"), person2); // creator of item
        System.out.println(todoItem.getSummary());
        System.out.println(todoItemTwo.getSummary());
        System.out.println();

        TodoItemTask todoItemTask = new TodoItemTask(todoItem, person2); // assignee to do itemTask
        TodoItemTask todoItemTaskTwo = new TodoItemTask(todoItemTwo);
        System.out.println(todoItemTask.getSummary());
        System.out.println(todoItemTaskTwo.getSummary());
        System.out.println();

        todoItem.setDone(true);
        System.out.println(todoItem.getSummary());
    }
}