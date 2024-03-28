package org.github.aastrandemma;

import org.github.aastrandemma.model.*;

import java.time.LocalDate;

public class App {
    public static void main( String[] args ) {
        Person person = new Person("Jane","Doe", "jane@doe.com");
        Person person2 = new Person("John", "Doe", "john@doe.com");
        System.out.println(person.toString());
        System.out.println(person.equals(person2));
        System.out.println(person.equals(person));
        System.out.println();

        TodoItem todoItem = new TodoItem("Work out", LocalDate.parse("2024-03-25"), person); // creator of item
        TodoItem todoItemTwo = new TodoItem("Buy food", "Milk, Eggs, Meat", LocalDate.parse("2024-03-20"), person2); // creator of item
        System.out.println(todoItem.toString());
        System.out.println(todoItemTwo.toString());
        System.out.println();

        TodoItemTask todoItemTask = new TodoItemTask(todoItem, person2); // assignee to do itemTask
        TodoItemTask todoItemTaskTwo = new TodoItemTask(todoItemTwo);
        System.out.println(todoItemTask.toString());
        System.out.println(todoItemTaskTwo.toString());
        System.out.println();

        todoItem.setDone(true);
        System.out.println(todoItemTask.toString());

        AppUser user = new AppUser("User123", "AbCd5454", AppRole.ROLE_APP_USER);
        AppUser admin = new AppUser("Admin456", "123456", AppRole.ROLE_APP_ADMIN);
        System.out.println(user);
        System.out.println(admin);
        System.out.println(user.hashCode());
        System.out.println(user.equals(user));
        System.out.println(admin.hashCode());
    }
}