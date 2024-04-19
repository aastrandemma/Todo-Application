package org.github.aastrandemma;

import org.github.aastrandemma.data.impl.AppUserDAOCollection;
import org.github.aastrandemma.data.impl.PersonDAOCollection;
import org.github.aastrandemma.data.impl.TodoItemDAOCollection;
import org.github.aastrandemma.data.impl.TodoItemTaskDAOCollection;

public class ToDoApp {
    public static void main(String[] args) {
        initialize();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down");
            System.out.println("Writing JSON");
            AppUserDAOCollection.getInstance().shutDownAppUser();
            PersonDAOCollection.getInstance().shutDownPerson();
            TodoItemDAOCollection.getInstance().shutDownTodoItem();
            TodoItemTaskDAOCollection.getInstance().shutDownTodoItemTask();
            JSONWriter.getInstance().writeSequencerValuesToPropertiesFile();
        }));
    }

    private static void initialize() {
        System.out.println("Starting up");
        System.out.println("Reading JSON");
        PersonDAOCollection.getInstance().initializePerson();
        AppUserDAOCollection.getInstance().initializeAppUser();
        TodoItemDAOCollection.getInstance().initializeTodoItem();
        TodoItemTaskDAOCollection.getInstance().initializeTodoItemTask();
        JSONReader.getInstance().readPropertiesToSequencers();
        System.out.println();
    }
}