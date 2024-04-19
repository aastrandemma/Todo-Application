package org.github.aastrandemma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class JSONWriter<T> {
    private static JSONWriter instance;

    public static JSONWriter getInstance() {
        if (instance == null) {
            instance = new JSONWriter();
        }
        return instance;
    }

    private JSONWriter(){}

    public void writeFromListToJson(List<T> list, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File("JSONFiles/" + fileName + ".json");

        // ToDo: check why it won't work
        if (file.exists()) {
            boolean deleteFile = file.delete();
            if (!deleteFile) {
                System.out.println("Failed to delete existing file for: " + fileName);
            }
        }

        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void writeSequencerValuesToPropertiesFile() {
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir") + "\\JSONFiles\\properties.json\\";
        String message = "Sequencer data in properties file";
        properties.setProperty("personIdSequencer", String.valueOf(PersonIdSequencer.getInstance().getCurrentId()));
        properties.setProperty("todoItemIdSequencer", String.valueOf(TodoItemIdSequencer.getInstance().getCurrentId()));
        properties.setProperty("todoItemTaskIdSequencer", String.valueOf(TodoItemTaskIdSequencer.getInstance().getCurrentId()));

        try {
            FileOutputStream file = new FileOutputStream(filePath);
            properties.store(file, message);
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}