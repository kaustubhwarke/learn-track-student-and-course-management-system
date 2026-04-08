package com.airtribe.learntrack.service.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.List;

public interface CourseService {

    /*
     * CourseServiceImpl Management
     * Add new course
     * View all courses
     * Activate/Deactivate a course
     * */

    Course addCourse(Course course);

    List<Course> getAllCourses();

    Course updateCourse(Course course) throws EntityNotFoundException;

    Course getCourseById(Integer id) throws EntityNotFoundException;


}
