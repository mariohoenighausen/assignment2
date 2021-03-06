package org.hbrs.ia.model;

public class SalesMan {
    private int sid;
    private String firstName;
    private String lastName;
    private String dob;
    private String experience;

    public SalesMan(int sid, String firstName, String lastName, String dob, String experience) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.experience = experience;
    }

    public SalesMan(String firstName, String lastName, String dob, String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.experience = experience;
    }

    public SalesMan() {

    }

    public int getSid() {
        return sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "SalesMan{" +
                "sid=" + sid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
