package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PatientController {
    private Comparator<Patient> comparator;
    private PriorityQueue<Patient> patients;

    public PatientController() {
    }

    public PatientController(Comparator<Patient> comparator) {
        this.comparator = comparator;
        this.patients = new PriorityQueue<>();
    }

    public String getTurn(){
        return "A 12";
    }
}
