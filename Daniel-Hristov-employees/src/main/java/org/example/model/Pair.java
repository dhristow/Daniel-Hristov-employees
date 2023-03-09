package org.example.model;

public class Pair {

    private int firstEmpId;
    private int secondEmpId;
    private int duration;

    private int projectId;

    public Pair(int firstEmpId, int secondEmpId, int duration, int projectId) {
        this.firstEmpId = firstEmpId;
        this.secondEmpId = secondEmpId;
        this.duration = duration;
        this.projectId = projectId;
    }

    public int getFirstEmpId() {
        return firstEmpId;
    }

    public Pair setFirstEmpId(int firstEmpId) {
        this.firstEmpId = firstEmpId;
        return this;
    }

    public int getSecondEmpId() {
        return secondEmpId;
    }

    public Pair setSecondEmpId(int secondEmpId) {
        this.secondEmpId = secondEmpId;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public int setDuration(int duration) {
        return this.duration = duration;
    }

    public void addDuration(int additionalDuration) {
        this.duration += additionalDuration;
    }

    public int getProjectId() {
        return projectId;
    }

    public Pair setProjectId(int projectId) {
        this.projectId = projectId;
        return this;
    }
}
