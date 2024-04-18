package org.github.aastrandemma;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemIdSequencer;
import org.github.aastrandemma.data.sequencer.TodoItemTaskIdSequencer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JSONReader<T> {
    private static JSONReader instance;

    public static JSONReader getInstance() {
        if (instance == null) {
            instance = new JSONReader();
        }
        return instance;
    }

    private JSONReader(){}

    public List<T> readToList(String fileName) {
        List<T> storage = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("JSONFiles/" + fileName + ".json");

        try {
            storage = objectMapper.readValue(file, new TypeReference<List<T>>() {});
            System.out.println(fileName + " value added to list.");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return storage;
    }

    public void readPropertiesToSequencers(){
        String filePath = "JSONFiles/properties.json";
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        PersonIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("personIdSequencer")));
        TodoItemIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("todoItemIdSequencer")));
        TodoItemTaskIdSequencer.getInstance().setCurrentId(Integer.parseInt(properties.getProperty("todoItemTaskIdSequencer")));
    }
}