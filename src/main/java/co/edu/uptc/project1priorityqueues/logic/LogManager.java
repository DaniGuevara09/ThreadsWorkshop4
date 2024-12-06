package co.edu.uptc.project1priorityqueues.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LogManager {
    private final List<Object> logs = new ArrayList<>();
    private final String filePath;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LogManager(String filePath) {
        this.filePath = filePath;
    }

    public void addLog(Object log) {
        logs.add(log);
    }

    public void saveLogs() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(logs, writer);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar logs: " + e.getMessage(), e);
        }
    }
}
