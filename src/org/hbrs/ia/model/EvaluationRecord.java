package org.hbrs.ia.model;

public class EvaluationRecord {
    private int erid;
    private int actualValue;
    private int targetValue;
    private int year;
    private String goalDesc;
    private int sid;

    public EvaluationRecord(int erid, int actualValue, int targetValue, int year, String goalDesc, int sid) {
        this.erid = erid;
        this.actualValue = actualValue;
        this.targetValue = targetValue;
        this.year = year;
        this.goalDesc = goalDesc;
        this.sid = sid;
    }
    public EvaluationRecord(){

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

    public String getGoalDesc() {
        return goalDesc;
    }

    public int getSid() {
        return sid;
    }

    @Override
    public String toString() {
        return "EvaluationRecord{" +
                "erid=" + erid +
                ", actualValue=" + actualValue +
                ", targetValue=" + targetValue +
                ", year=" + year +
                ", goal_desc='" + goalDesc + '\'' +
                ", sid=" + sid +
                '}';
    }
}
