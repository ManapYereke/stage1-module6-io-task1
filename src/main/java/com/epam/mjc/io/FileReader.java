package com.epam.mjc.io;

import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String fileData = readFileToString(file);
        Profile profile = parseFileData(fileData);
        return profile;
    }

    public String readFileToString(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    public Profile parseFileData(String fileData) {
        String[] lines = fileData.split("\n");
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0;

        for (String line : lines) {
            if (line.startsWith("Name:")) {
                name = line.substring(6).trim();
            } else if (line.startsWith("Age:")) {
                age = Integer.parseInt(line.substring(5).trim());
            } else if (line.startsWith("Email:")) {
                email = line.substring(7).trim();
            } else if (line.startsWith("Phone:")) {
                phone = Long.parseLong(line.substring(7).trim());
            }
        }

        return new Profile(name, age, email, phone);
    }
}
