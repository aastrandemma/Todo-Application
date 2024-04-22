package org.github.aastrandemma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingConfigurator {
    public static void configureLogging(String filePath) {
        try {
            Path loggingPropertiesFile = Paths.get("resources/" + filePath);
            if (Files.exists(loggingPropertiesFile)) {
                LogManager.getLogManager().readConfiguration(Files.newInputStream(loggingPropertiesFile));
            } else {
                Logger.getLogger(LoggingConfigurator.class.getName()).warning("Logging configuration file not found: " + filePath);
            }
        } catch (IOException e) {
            Logger.getLogger(LoggingConfigurator.class.getName()).log(Level.SEVERE, "Error loading logging configuration", e);
        }
    }
}