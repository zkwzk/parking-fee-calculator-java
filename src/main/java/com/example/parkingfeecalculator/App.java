package com.example.parkingfeecalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private static final String COUNT_FILE = "count.txt";

    public static void main(String[] args) {
        try {
            int count = readCountFromFile();
            count++;
            writeCountToFile(count);
            System.out.println("Application started " + count + " times.");
            SpringApplication.run(App.class, args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int readCountFromFile() throws IOException {
        File file = new File(COUNT_FILE);
        if (!file.exists()) {
            file.createNewFile();
            return 0;
        }
        String content = new String(Files.readAllBytes(Paths.get(COUNT_FILE)));
        return content.isEmpty() ? 0 : Integer.parseInt(content.trim());
    }

    private static void writeCountToFile(int count) throws IOException {
        try (FileWriter writer = new FileWriter(COUNT_FILE)) {
            writer.write(String.valueOf(count));
        }
    }
}
