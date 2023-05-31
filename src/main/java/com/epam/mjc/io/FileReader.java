package com.epam.mjc.io;

import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String name = "", email = "";
        int age = 0;
        long phone = 0L;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String str = "";
            String line;
            while ((line = reader.readLine()) != null) {
                str += line + "\n";
            }
            String[] items = str.split("\n");

            for (int i = 0; i < items.length; i++) {
                if (items[i].startsWith("Name:")) {
                    name = items[i].substring(6).trim();
                } else if (items[i].startsWith("Age:")) {
                    age = Integer.parseInt(items[i].substring(5).trim());
                } else if (items[i].startsWith("Email:")) {
                    email = items[i].substring(7).trim();
                } else if (items[i].startsWith("Phone:")) {
                    phone = Long.parseLong(items[i].substring(7).trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(name, age, email, phone);
    }
}
