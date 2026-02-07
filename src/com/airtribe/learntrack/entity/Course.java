package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Course {

    /*
       Fields: id, courseName, description, durationInWeeks, active
     */

    private static int idCounter = 1;
    private Integer id;
    private String courseName;
    private String description;
    private Integer durationInWeeks;
    private Boolean active;

    public Course(String courseName, String description, Integer durationInWeeks, Boolean active) {
        this.id = IdGenerator.generateId();
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(Integer durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
