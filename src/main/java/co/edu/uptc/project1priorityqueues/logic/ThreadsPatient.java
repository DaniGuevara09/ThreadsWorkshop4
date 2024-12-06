package co.edu.uptc.project1priorityqueues.logic;

import java.util.ArrayList;
import java.util.Map;

public class ThreadsPatient implements Runnable {
    private PatientController controller;
    private int numOp;
    private LogManager logManager;

    public ThreadsPatient(PatientController controller, int numOp) {
        this.controller = controller;
        this.numOp = numOp;
        this.logManager = controller.getLogManager();
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
        long startTime = System.nanoTime();

        switch (numOp){
            case 1 -> controller.time1();
            case 2 -> controller.time2();
            case 3 -> controller.time3();
        }

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        logExecutionTime(executionTime);
    }

    private void logExecutionTime(long executionTime) {
        double executionTimeInMillis = executionTime / 1000000.0;
        int activeThreadCount = controller.getManualThreadsCount();

        logManager.addLog(Map.of(
                "event", "executionTime",
                "queue", "patients" + numOp,
                "executionTimeInMillis", executionTimeInMillis,
                "activeThreads", activeThreadCount
        ));
        controller.saveLogs();
    }
}
