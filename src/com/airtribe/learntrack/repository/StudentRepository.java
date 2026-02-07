package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Find a student by ID
    public Student findById(Integer id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update an existing student
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    // Delete a student by ID
    public boolean deleteStudent(Integer id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    // Get all students
    public List<Student> findAll() {
        return students;
    }
}