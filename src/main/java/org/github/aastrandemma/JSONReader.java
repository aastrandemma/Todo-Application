package org.github.aastrandemma;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONReader<T> {
    private static final Logger logger = Logger.getLogger(JSONReader.class.getName());
    public static void initializeLogger(Logger parentLogger) {
        logger.setLevel(Level.INFO);
        logger.setParent(parentLogger);
    }
    private JSONReader(){}

    public static <T> List<T> readToList(String fileName) {
        List<T> storage = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Path filePath = Paths.get("JSONFiles/" + fileName + ".json");
        if(!Files.exists(filePath)) {
            logger.warning("File does not exist on startup: " + fileName + ".json");
            return storage;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            storage = objectMapper.readValue(reader, new TypeReference<List<T>>() {});
            logger.info(fileName + ".json values have been initialized from the file.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading JSON from file: " + e);
        }

        return storage;
    }

    public static void readPropertiesToSequencers(){
        String fileName = "properties";
        Path filePath = Paths.get("JSONFiles/" + fileName + ".json");
        Properties properties = new Properties();

        if(!Files.exists(filePath)) {
            logger.warning("File does not exist on startup: " + fileName + ".json");
            return;
        }

        try {
            properties.load(Files.newInputStream(filePath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading properties file", e);
            return;
        }

        PersonIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("personIdSequencer")));
        TodoItemIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("todoItemIdSequencer")));
        TodoItemTaskIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("todoItemTaskIdSequencer")));

        logger.info(fileName +".json values have been initialized from the file.");
    }
}