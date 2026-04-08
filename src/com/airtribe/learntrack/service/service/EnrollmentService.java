package com.airtribe.learntrack.service.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.List;

public interface EnrollmentService {

    /*
     * EnrollmentServiceImpl Management
     * Enroll a student in a course
     * View enrollments for a student
     * Mark enrollment as completed/cancelled
     * */

    Enrollment enrollStudentInCourse(Enrollment enrollment);

    List<Enrollment> getEnrollmentsByStudentId(Integer studentId) throws EntityNotFoundException;

    Enrollment updateEnrollmentStatus(Enrollment enrollment) throws EntityNotFoundException;
}
