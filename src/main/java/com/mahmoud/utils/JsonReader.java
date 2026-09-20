package com.mahmoud.utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static String readJsonFile(String filePath) {

        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Could not read JSON file: " + filePath, e);
        }
    }
}