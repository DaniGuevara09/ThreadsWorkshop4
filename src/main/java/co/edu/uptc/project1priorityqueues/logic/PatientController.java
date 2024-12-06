package co.edu.uptc.project1priorityqueues.logic;

import co.edu.uptc.project1priorityqueues.model.Patient;
import javafx.scene.image.Image;

import java.util.*;

public class PatientController {
    //private TreeSet<Patient> patients1;
    private PriorityQueue<Patient> patients1;
    private PriorityQueue<Patient> patients2;
    private PriorityQueue<Patient> patients3;
    private PriorityQueue<Patient> patients4;
    private ArrayList<Patient> allPatients;

    private int contDisabled;
    private int contPregnant;
    private int contAge;
    private int contOther;

    private int time1;
    private int time2;
    private int time3;
    private int time4;

    public PatientController() {
        allPatients = new ArrayList<>();
    }

    public PatientController(Comparator<Patient> comparator) {
        allPatients = new ArrayList<>();
        this.patients1 = new PriorityQueue<>(comparator);
        this.patients2 = new PriorityQueue<>(comparator);
        this.patients3 = new PriorityQueue<>(comparator);
        this.patients4 = new PriorityQueue<>(comparator);
        //patients1 = new TreeSet<>(comparator);

        contDisabled = 1;
        contPregnant = 1;
        contAge = 1;
        contOther = 1;

        time1 = 0;
        time2 = 0;
        time3 = 0;
        time4 = 0;
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
            case 4 -> {
                for (Patient patient : patients4) {
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
            case 4 -> aux = new PriorityQueue<>(patients4);
        }

        ArrayList<Patient> patientList = new ArrayList<>();

        while (!aux.isEmpty()) {
            patientList.add(aux.poll());
        }
        return patientList;
    }

    public void deleteFirstA(int numList) {

    }


    public void deleteFirst(int numList){
        //patients1.pollFirst();
        switch (numList){
            case 1 -> patients1.poll();
            case 2 -> patients2.poll();
            case 3 -> patients3.poll();
            case 4 -> patients4.poll();
        }
    }

    public String addPatient(boolean disabled, boolean pregnant, String ageRange){
        Patient newPatient = new Patient(disabled, pregnant, ageRange, timeRandom());
        String turn = newTurn(newPatient);

        int size1 = patients1.size();
        int size2 = patients2.size();
        int size3 = patients3.size();
        int size4 = patients4.size();

        if (size1 <= size2 && size2 <= size3 && size3 <= size4) {
            patients1.offer(newPatient);
        } else if (size2 <= size1 && size2 <= size3 && size2 <= size4) {
            patients2.offer(newPatient);
        } else if (size3 <= size1 && size3 <= size2 && size3 <= size4) {
            patients3.offer(newPatient);
        } else if (size4 <= size1) {
            patients4.offer(newPatient);
        }

        allPatients.add(newPatient);
        return turn;
    }

    public static int timeRandom(){
        return (int) (Math.random() * 11) + 5;
    }

    public String newTurn(Patient patient){
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

    public void time4(){
        //System.out.println("\nTime 4");

        try{
            time4 = patients4.stream().findFirst().get().getTime();
        } catch (Exception e){
        }

//        int durationInSeconds = 10; // Duración total en segundos
//        int intervalInSeconds = 1; // Intervalo entre cambios
//        String[] MESSAGES = {"Mensaje 1", "Mensaje 2", "Mensaje 3", "Mensaje 4"};
//        int index = 0;
//
//        long endTime = System.currentTimeMillis() + (durationInSeconds * 1000);
//        while (System.currentTimeMillis() < endTime) {
//            System.out.println(MESSAGES[index]);
//            index = (index + 1) % MESSAGES.length; // Avanza al siguiente mensaje
//            try {
//                Thread.sleep(intervalInSeconds * 1000); // Espera 1 segundo
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
//        }
//        System.out.println("Cambio de mensajes finalizado.");
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

    public int getTime4() {
        return time4;
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