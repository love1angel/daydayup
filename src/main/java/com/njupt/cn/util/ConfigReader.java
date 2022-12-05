package com.njupt.cn.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.njupt.cn.model.Person;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class ConfigReader {
    private final String path = "src/main/resources/data.json";

    private static ConfigReader instance;
    private File file;
    private StringBuilder json = new StringBuilder();

    private ConfigReader() {
    }

    public static synchronized ConfigReader getInstance() {
        instance = new ConfigReader();
        return instance;
    }

    public List<Person> init() throws Exception {
        file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            json.append(scanner.nextLine());
        }
        Gson gson = new Gson();
        List<Person> persons = gson.fromJson(json.toString(), new TypeToken<List<Person>>() {
        }.getType());
        return persons;
    }

    public void shutdown(List<Person> persons) throws Exception {
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        fileWriter.write(gson.toJson(persons));
        fileWriter.flush();
    }
}
