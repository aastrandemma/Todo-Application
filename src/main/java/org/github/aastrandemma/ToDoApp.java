package org.github.aastrandemma;

import org.github.aastrandemma.data.impl.AppUserDAOCollection;
import org.github.aastrandemma.data.impl.PersonDAOCollection;
import org.github.aastrandemma.data.impl.TodoItemDAOCollection;
import org.github.aastrandemma.data.impl.TodoItemTaskDAOCollection;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToDoApp {
    /**
     * Logger for the ToDoApp class
     * This logger is used to log messages related to the ToDoApp class and its operations.
     * Static initializer block to configure logging for the ToDoApp class.
     * This block initializes the logger with the logging configuration specified in the 'logging.properties' file.
     * It ensures that the logger is properly configured before any log messages are generated.
     */
    private static final Logger logger = Logger.getLogger(ToDoApp.class.getName());
    static {
        LoggingConfigurator.configureLogging("logging.properties");
        JSONReader.initializeLogger(logger);
        JSONWriter.initializeLogger(logger);
        PersonDAOCollection.initializeLogger(logger);
        TodoItemDAOCollection.initializeLogger(logger);
        TodoItemTaskDAOCollection.initializeLogger(logger);
    }

    @FunctionalInterface
    interface Action {
        void perform();
    }

    /**
     * Initialize
     * An action representing the initialization process of the application.
     * This action is executed to load data into various objects and lists at startup.
     */
    static Action initialize = () -> {
        logger.log(Level.INFO, "Starting up...");
        logger.log(Level.INFO, "Reading JSON");

        PersonDAOCollection.getInstance().initializePerson();
        AppUserDAOCollection.getInstance().initializeAppUser();
        TodoItemDAOCollection.getInstance().initializeTodoItem();
        TodoItemTaskDAOCollection.getInstance().initializeTodoItemTask();
        JSONReader.readPropertiesToSequencers();
    };

    /**
     * Exit
     * An action representing the shutdown process of the application.
     * This action is executed to save data to JSON files.
     */
    static Action exit = () -> {
        // ToDo: Fix so these methods are under Runtime.getRuntime().addShutdownHook...
        // They are outside the hook because it won't write to the log in the hook.
        logger.log(Level.INFO, "Writing JSON");

        AppUserDAOCollection.getInstance().shutDownAppUser();
        PersonDAOCollection.getInstance().shutDownPerson();
        TodoItemDAOCollection.getInstance().shutDownTodoItem();
        TodoItemTaskDAOCollection.getInstance().shutDownTodoItemTask();
        JSONWriter.writeSequencerValuesToPropertiesFile();

        logger.log(Level.INFO, "Shutting down...");
    };

    public static void main(String[] args) {
        try {
            logger.info("Application started");
            initialize.perform();
            exit.perform();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                for (Handler handler : logger.getHandlers()) {
                    handler.flush();
                }
            }));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Runtime.getRuntime().exit(1);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}