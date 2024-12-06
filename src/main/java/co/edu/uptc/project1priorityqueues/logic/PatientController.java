package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;
import java.util.*;

public class PatientController {
    //private TreeSet<Patient> patients1;
    private PatientPersistence persistence;
    private PriorityQueue<Patient> patients1;
    private PriorityQueue<Patient> patients2;
    private PriorityQueue<Patient> patients3;
    private ArrayList<Patient> allPatients;

    private int contDisabled;
    private int contPregnant;
    private int contAge;
    private int contOther;

    private int time1;
    private int time2;
    private int time3;

    private Config config;
    private LogManager logManager;

    public PatientController() {
        allPatients = new ArrayList<>();
    }

    public PatientController(Comparator<Patient> comparator) {
        this.config = Config.load("src/main/java/co/edu/uptc/project1priorityqueues/persistence/config.json");
        this.logManager = new LogManager(config.logFilePath);

        allPatients = new ArrayList<>();
        persistence = new PatientPersistence();
        allPatients = persistence.loadPatientsFromFile();

        this.patients1 = new PriorityQueue<>(comparator);
        this.patients2 = new PriorityQueue<>(comparator);
        this.patients3 = new PriorityQueue<>(comparator);

        contDisabled = 0;
        contPregnant = 0;
        contAge = 0;
        contOther = 0;

        time1 = 0;
        time2 = 0;
        time3 = 0;
    }

    public List<String> getTurn(int numList){
        List <String> turnList = new ArrayList<>();

        switch (numList){
            case 1 -> {
                for (Patient patient : patients1) {
                    turnList.add(patient.getTurn());
                }
            }
            case 2 -> {
                for (Patient patient : patients2) {
                    turnList.add(patient.getTurn());
                }
            }
            case 3 -> {
                for (Patient patient : patients3) {
                    turnList.add(patient.getTurn());
                }
            }
        }
        return turnList;
    }

    public ArrayList<Patient> getPatients(int numList) {
        PriorityQueue<Patient> aux = new PriorityQueue<>();

        switch (numList){
            case 1 -> aux = new PriorityQueue<>(patients1);
            case 2 -> aux = new PriorityQueue<>(patients2);
            case 3 -> aux = new PriorityQueue<>(patients3);
        }

        ArrayList<Patient> patientList = new ArrayList<>();

        while (!aux.isEmpty()) {
            patientList.add(aux.poll());
        }
        return patientList;
    }

    public void deleteFirst(int numList) {
        Patient removedPatient = null;

        switch (numList) {
            case 1 -> removedPatient = patients1.poll();
            case 2 -> removedPatient = patients2.poll();
            case 3 -> removedPatient = patients3.poll();
        }

        if (removedPatient != null) {
            logManager.addLog(Map.of(
                    "event", "deleteFirst",
                    "queue", "patients" + numList,
                    "removedPatient", removedPatient.getTurn()
            ));
        }
        saveLogs();
    }

    public String addPatient(boolean disabled, boolean pregnant, String ageRange) {
        Patient newPatient = new Patient(disabled, pregnant, ageRange, timeRandom());
        String turn = newTurn(newPatient);

        int size1 = patients1.size();
        int size2 = patients2.size();
        int size3 = patients3.size();

        if (size1 <= size2 && size2 <= size3) {
            patients1.offer(newPatient);
        } else if (size2 <= size1 && size2 <= size3) {
            patients2.offer(newPatient);
        } else if (size3 <= size1) {
            patients3.offer(newPatient);
        }

        allPatients.add(newPatient);
        persistence.savePatientsToFile(allPatients);

        logManager.addLog(Map.of(
                "event", "addPatient",
                "patient", Map.of(
                        "turn", newPatient.getTurn(),
                        "disabled", newPatient.isDisabled(),
                        "pregnant", newPatient.isPregnant(),
                        "ageRange", newPatient.getAgeRange(),
                        "time", newPatient.getTime()
                )
        ));
        saveLogs();
        return turn;
    }

    public void saveLogs() {
        logManager.saveLogs();
    }

    public static int timeRandom(){
        return (int) (Math.random() * 11) + 5;
    }

    public String newTurn(Patient patient){
        updateCont();
        String turn;

        if (patient.isDisabled()){
            turn = "A" + contDisabled++;
        } else if (patient.isPregnant()){
            turn = "B" + contPregnant++;
        } else if (patient.getAgeRange().equals("≥ 60")){
            turn = "C" + contAge++;
        } else {
            turn = "D" + contOther++;
        }
        patient.setTurn(turn);
        return  turn;
    }

    public void updateCont(){
        contDisabled = 0;
        contPregnant = 0;
        contAge = 0;
        contOther = 0;

        try {
            for (Patient patient : allPatients) {
                if (patient.getTurn().contains("A")){
                    contDisabled++;
                } else if (patient.getTurn().contains("B")){
                    contPregnant++;
                } else if (patient.getTurn().contains("C")) {
                    contAge++;
                } else if (patient.getTurn().contains("D")) {
                    contOther++;
                }
            }
        } catch (Exception _){}
    }

    public void time1(){
        //System.out.println("\nTime 1");
        try{
            time1 = patients1.stream().findFirst().get().getTime();
        } catch (Exception _){
        }
    }

    public void time2(){
        //System.out.println("\nTime 2");
        try{
            time2 = patients2.stream().findFirst().get().getTime();
        } catch (Exception e){
        }
    }

    public void time3(){
        //System.out.println("\nTime 3");

        try{
            time3 = patients3.stream().findFirst().get().getTime();
        } catch (Exception e){
        }
    }

    public int getTime1() {
        return time1;
    }

    public int getTime2() {
        return time2;
    }

    public int getTime3() {
        return time3;
    }

    public LogManager getLogManager() {
        return logManager;
    }

    public static class PatientComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient p1, Patient p2) {

            //System.out.println(p1.getTurn());
            //System.out.println(p2.getTurn());

            if (p1.getTurn().compareTo(p2.getTurn()) > 0){
                return 1;
            } else if (p1.getTurn().compareTo(p2.getTurn()) < 0){
                return -1;
            } else {
                if (p1.getTurn().substring(1).compareTo(p2.getTurn().substring(1)) > 0){
                    return 1;
                } else{
                    return -1;
                }
            }
        }
    }
}