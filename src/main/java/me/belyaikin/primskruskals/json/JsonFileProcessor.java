package me.belyaikin.primskruskals.json;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class JsonFileProcessor {
    public static String getInputJsonFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Error accessing the file: " + e.getMessage());
        }

        return null;
    }

    public static void createResultsJsonFile(String path, String json) {
        File jsonFile = new File(path);

        try {
            if (jsonFile.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(path);

                    writer.write(json);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Failed to write to a file: " + e.getMessage());
                }
                System.out.println("Successfully recorded results to: " + path);
            }
            else {
                System.out.println("Failed to record results to: " + path);
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file: " + e.getMessage());
        }
    }
}
