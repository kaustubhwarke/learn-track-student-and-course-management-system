package com.airtribe.learntrack.entity;

public class Student extends Person {
    /*
    id, firstName, lastName, email, batch, active (boolean)
     */

    private String batch;
    private Boolean active;

    public Student(String firstName, String lastName, String email, String batch, Boolean active) {
        super(firstName, lastName, email);
        this.batch = batch;
        this.active = active;

    }

    public Student(String firstName, String lastName, String batch, Boolean active) {
        super(firstName, lastName);
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
