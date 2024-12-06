package co.edu.uptc.project1priorityqueues.logic;

import java.util.ArrayList;

public class ThreadsPatient implements Runnable {
    private PatientController controller;
    private int numOp;

    public ThreadsPatient(PatientController controller, int numOp) {
        this.controller = controller;
        this.numOp = numOp;
    }

    public ArrayList<Integer> getTime(){
        ArrayList<Integer> aux = new ArrayList<>();

        aux.add(controller.getTime1());
        aux.add(controller.getTime2());
        aux.add(controller.getTime3());
        return aux;
    }

    @Override
    public void run() {
        switch (numOp){
            case 1 -> controller.time1();
            case 2 -> controller.time2();
            case 3 -> controller.time3();
        }
    }
}
