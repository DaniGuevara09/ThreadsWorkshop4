package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

    public ArrayList<Patient> loadPatientsFromFile() {
        ArrayList<Patient> allPatients = new ArrayList<>();

        try (FileReader reader = new FileReader(jsonFile)) {
            Type listType = new TypeToken<ArrayList<Patient>>() {}.getType();
            ArrayList<Patient> patients = gson.fromJson(reader, listType);
            if (patients != null) {
                allPatients.addAll(patients);
            }
            return allPatients;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}
