package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

import java.util.Date;

public class Enrollment {

    /*
    * Fields: id, studentId, courseId, enrollmentDate, status (e.g., "ACTIVE", "COMPLETED", "CANCELLED" as String or simple enum if you want slightly advanced)
    * */
    private static int idCounter = 1;
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Date enrollmentDate;
    private String status;

    public Enrollment(Integer studentId, Integer courseId, Date enrollmentDate, String status) {
        this.id = IdGenerator.generateId();
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
