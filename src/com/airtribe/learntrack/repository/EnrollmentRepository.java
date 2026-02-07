package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    // Add a new enrollment
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    // Find an enrollment by ID
    public List<Enrollment> findById(Integer id) {
//        return enrollments.stream()
//                .filter(enrollment -> enrollment.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudentId().equals(id))
                .toList();


    }

    // Update an existing enrollment
    public boolean updateEnrollment(Enrollment updatedEnrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId().equals(updatedEnrollment.getId())) {
                enrollments.set(i, updatedEnrollment);
                return true;
            }
        }
        return false;
    }

    // Delete an enrollment by ID
    public boolean deleteEnrollment(Integer id) {
        return enrollments.removeIf(enrollment -> enrollment.getId().equals(id));
    }

    // Get all enrollments
    public List<Enrollment> findAll() {
        return enrollments;
    }
}