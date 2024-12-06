package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PatientPersistence {
    private static String path = "src/main/java/co/edu/uptc/project1priorityqueues/persistence";
    private static String fileName = "PatientTurn.json";
    private Gson gson;
    private File jsonFile;

    public PatientPersistence() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();;
        this.jsonFile = new File(path, fileName);
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            File directory = new File(path);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio: " + path);
                }
            }
            if (!jsonFile.exists()) {
                if (jsonFile.createNewFile()) {
                    savePatientsToFile(null); // Crea un archivo inicial vacío
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo JSON: " + e.getMessage());
        }
    }

    public void savePatientsToFile(ArrayList<Patient> allPatients) {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            gson.toJson(allPatients, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo JSON: " + e.getMessage());
        }
    }
}
