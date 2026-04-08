package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Person {
    /*
    Fields: id, firstName, lastName, email
     */

    private static int idCounter = 1;
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.id = IdGenerator.generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName) {
        this.id = IdGenerator.generateId();
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDisplayName() {
        return this.getFirstName() + " " + this.getLastName();
    }


}
