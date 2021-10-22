package org.hbrs.ia.model;

public class EvaluationRecord {
    private int erid;
    private int actualValue;
    private int targetValue;
    private int year;
    private String goal_desc;
    private int sid;

    public EvaluationRecord(int erid, int actualValue, int targetValue, int year, String goal_desc, int sid) {
        this.erid = erid;
        this.actualValue = actualValue;
        this.targetValue = targetValue;
        this.year = year;
        this.goal_desc = goal_desc;
        this.sid = sid;
    }

    public int getErid() {
        return erid;
    }

    public int getActualValue() {
        return actualValue;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public int getYear() {
        return year;
    }

    public String getGoal_desc() {
        return goal_desc;
    }

    public int getSid() {
        return sid;
    }
}
