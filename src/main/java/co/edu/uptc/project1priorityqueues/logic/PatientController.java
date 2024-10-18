package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PatientController {
    private PriorityQueue<Patient> patients;

    private int contDisabled;
    private int contPregnant;
    private int contAge;
    private int contOther;

    public PatientController() {}

    public PatientController(Comparator<Patient> comparator) {
        this.patients = new PriorityQueue<>(comparator);

        contDisabled = 0;
        contPregnant = 0;
        contAge = 0;
        contOther = 0;
    }

    public List<String> getTurn(){
        List <String> turnList = new ArrayList<>();

        for (Patient patient : patients) {
            turnList.add(patient.getTurn());
        }
        return turnList;
    }

    public void addPatient(boolean disabled, boolean pregnant, String ageRange){
        Patient newPatient = new Patient(disabled, pregnant, ageRange);
        newTurn(newPatient);
        patients.offer(newPatient);
    }

    public void newTurn(Patient patient){
        String turn;

        if (patient.isDisabled()){
            turn = "A - " + contDisabled++;
        } else if (patient.isPregnant()){
            turn = "B - " + contPregnant++;
        } else if (patient.getAgeRange().equals("≥ 60")){
            turn = "C - " + contAge++;
        } else {
            turn = "D - " + contOther++;
        }
        patient.setTurn(turn);
    }

    public static class PatientComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient p1, Patient p2) {
            // 1. Disabled
            if (p1.isDisabled() && !p2.isDisabled()) return -1;
            if (!p1.isDisabled() && p2.isDisabled()) return 1;

            // 2. Pregnant
            if (p1.isPregnant() && !p2.isPregnant()) return -1;
            if (!p1.isPregnant() && p2.isPregnant()) return 1;

            // 3. Age
            if (p1.getAgeRange().equals("≥ 60") && !p2.getAgeRange().equals("≥ 60")) return -1;
            if (!p1.getAgeRange().equals("≥ 60") && p2.getAgeRange().equals("≥ 60")) return 1;

            return 0;
        }
    }
}