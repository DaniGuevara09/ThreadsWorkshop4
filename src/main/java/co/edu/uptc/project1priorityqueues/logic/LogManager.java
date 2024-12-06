package co.edu.uptc.project1priorityqueues.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogManager {
    private List<Map<String, Object>> logs = new ArrayList<>();

    private final String filePath;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LogManager(String filePath) {
        this.filePath = filePath;
    }

    public void addLog(Map<String, Object> log) {
        synchronized (this) {
            logs.add(log);
        }
    }

    public void saveLogs() {
        synchronized (this) {
            try (FileWriter writer = new FileWriter(filePath)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(logs, writer);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar logs: " + e.getMessage(), e);
            }
        }
    }
}
