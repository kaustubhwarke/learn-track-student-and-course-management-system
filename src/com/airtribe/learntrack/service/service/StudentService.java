package com.airtribe.learntrack.service.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.List;

public interface StudentService {
    /*
    Add new student
    View all students
    Search student by ID
    Deactivate a student (set active = false instead of deleting)

    In services: addStudent, removeStudent, updateStudent, listStudents

    */


    Student addStudent(Student student);

    Integer removeStudent(Integer studentId) throws EntityNotFoundException;

    Student updateStudent(Student studentToUpdate) throws EntityNotFoundException;

    Student searchStudentById(Integer studentId) throws EntityNotFoundException;

    Student deactivateStudent(Integer studentId) throws EntityNotFoundException;

    List<Student> viewAllStudents();
}
