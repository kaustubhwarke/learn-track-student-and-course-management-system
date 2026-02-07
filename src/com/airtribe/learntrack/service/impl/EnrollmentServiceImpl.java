package com.airtribe.learntrack.service.impl;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.service.service.EnrollmentService;

import java.util.Date;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment enrollStudentInCourse(Enrollment enrollment) {
        enrollmentRepository.addEnrollment(enrollment);
        return enrollment;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Integer studentId) throws EntityNotFoundException {
        List<Enrollment> enrollments = enrollmentRepository.findById(studentId);
        if (enrollments.isEmpty()) {
            throw new EntityNotFoundException("No enrollments found for student ID: " + studentId);
        }
        return enrollments;
    }

    @Override
    public Enrollment updateEnrollmentStatus(Enrollment enrollment) throws EntityNotFoundException {
        if (enrollmentRepository.updateEnrollment(enrollment)) {
            return enrollment;
        } else {
            throw new EntityNotFoundException("Enrollment not found with the given Id: " + enrollment.getId());
        }

    }
}
