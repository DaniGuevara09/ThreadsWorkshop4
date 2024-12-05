package co.edu.uptc.project1priorityqueues.logic;

public class ThreadsPatient implements Runnable {
    private PatientController controller;
    private int numOp;

    public ThreadsPatient(PatientController controller, int numOp) {
        this.controller = controller;
        this.numOp = numOp;
    }

    @Override
    public void run() {
        switch (numOp){
            case 1 -> controller.time1();
            case 2 -> controller.time2();
            case 3 -> controller.time3();
            case 4 -> controller.time4();
        }
    }
}
