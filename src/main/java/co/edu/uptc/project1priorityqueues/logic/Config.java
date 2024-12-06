package co.edu.uptc.project1priorityqueues.logic;

import com.google.gson.Gson;
import java.io.FileReader;

public class Config {
    public String logFilePath;

    public static Config load(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return new Gson().fromJson(reader, Config.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar configuración: " + e.getMessage(), e);
        }
    }
}
