package org.github.aastrandemma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONWriter<T> {
    private static final Logger logger = Logger.getLogger(JSONWriter.class.getName());
    public static void initializeLogger(Logger parentLogger) {
        logger.setLevel(Level.INFO);
        logger.setParent(parentLogger);
    }
    private JSONWriter(){}

    public static <T> void writeFromListToJson(List<T> list, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path filePath = Paths.get("JSONFiles/" + fileName + ".json");

        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
                logger.info("File has been deleted for: " + fileName);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error deleting file. Exception: " + e);
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.CREATE_NEW)) {
            objectMapper.writeValue(writer, list);
            logger.info("JSON data has been written for: " + fileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON data for: " + fileName + " Exception: " + e);
        }
    }

    public static void writeSequencerValuesToPropertiesFile() {
        Properties properties = new Properties();
        Path filePath = Paths.get("JSONFiles/properties.json");
        String message = "Sequencer data";
        properties.setProperty("personIdSequencer", String.valueOf(PersonIdSequencer.getInstance().getCurrentId()));
        properties.setProperty("todoItemIdSequencer", String.valueOf(TodoItemIdSequencer.getInstance().getCurrentId()));
        properties.setProperty("todoItemTaskIdSequencer", String.valueOf(TodoItemTaskIdSequencer.getInstance().getCurrentId()));

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
            properties.store(writer, message);
            logger.info("JSON data for sequencer values written to: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON data for sequencer values. Exception: " + e);
        }
    }
}