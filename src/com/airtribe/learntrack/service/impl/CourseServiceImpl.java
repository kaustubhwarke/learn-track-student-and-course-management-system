package com.airtribe.learntrack.service.impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.service.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    /*

     * CourseServiceImpl Management
     * Add new course
     * View all courses
     * Activate/Deactivate a course
     * */

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(Course course) {
        courseRepository.addCourse(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with the given Id: " + id);
        }
        return course;
    }

    @Override
    public Course updateCourse(Course course) throws EntityNotFoundException {

        if (courseRepository.updateCourse(course)) {
            return course;
        } else {
            throw new EntityNotFoundException("Course not found with the given Id: " + course.getId());
        }
    }


}
