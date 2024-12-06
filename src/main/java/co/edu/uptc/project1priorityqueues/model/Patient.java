package co.edu.uptc.project1priorityqueues.model;

public class Patient {
    private String turn;
    private boolean disabled;
    private boolean pregnant;
    private String ageRange;
    private int time;

    public Patient(boolean disabled, boolean pregnant, String ageRange, int time) {
        this.disabled = disabled;
        this.pregnant = pregnant;
        this.ageRange = ageRange;
        this.time = time;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public int getTime() {
        return time;
    }
}
