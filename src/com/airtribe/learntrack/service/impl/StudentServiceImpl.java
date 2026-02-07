package com.airtribe.learntrack.service.impl;


import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        studentRepository.addStudent(student);
        return student;
    }


    public Integer removeStudent(Integer studentId) throws EntityNotFoundException {
        if(studentRepository.deleteStudent(studentId)){
            return studentId;
        } else {
            throw new EntityNotFoundException("Student not found with the given Id: " + studentId);
        }
    }


    public Student updateStudent(Student studentToUpdate) throws EntityNotFoundException {

        if(studentRepository.updateStudent(studentToUpdate)){
            return studentToUpdate;
        } else {
            throw new EntityNotFoundException("Student not found with the given Id: " + studentToUpdate.getId());
        }

//        for (Student student : students) {
//            if (student.getId().equals(studentToUpdate.getId())) {
//                student.setFirstName(studentToUpdate.getFirstName());
//                student.setLastName(studentToUpdate.getLastName());
//                student.setEmail(studentToUpdate.getEmail());
//                student.setBatch(studentToUpdate.getBatch());
//                student.setActive(studentToUpdate.getActive());
//                return student;
//            }
//        }
//        return null;
    }


    public Student searchStudentById(Integer studentId) throws EntityNotFoundException {
        Student searchedStudent = studentRepository.findById(studentId);
        if(null != searchedStudent){
            return searchedStudent;
        } else {
            throw new EntityNotFoundException("Student not found with the given Id: " + studentId);
        }

        /*for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student not found with the given Id: " + studentId);*/
    }


    public Student deactivateStudent(Integer studentId) throws EntityNotFoundException {

        for (Student student : studentRepository.findAll()) {
            if (student.getId().equals(studentId)) {
                student.setActive(false);
                return student;
            }
        }

        throw new EntityNotFoundException("Student not found with the given Id: " + studentId);
    }


    public List<Student> viewAllStudents() {
        return studentRepository.findAll();
    }
}
