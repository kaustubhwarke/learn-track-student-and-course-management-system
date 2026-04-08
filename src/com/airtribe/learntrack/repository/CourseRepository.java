package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    // Add a new course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Find a course by ID
    public Course findById(Integer id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update an existing course
    public boolean updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(updatedCourse.getId())) {
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    // Delete a course by ID
    public boolean deleteCourse(Integer id) {
        return courses.removeIf(course -> course.getId().equals(id));
    }

    // Get all courses
    public List<Course> findAll() {
        return courses;
    }
}