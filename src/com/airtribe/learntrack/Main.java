package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.impl.CourseServiceImpl;
import com.airtribe.learntrack.service.impl.EnrollmentServiceImpl;
import com.airtribe.learntrack.service.impl.StudentServiceImpl;
import com.airtribe.learntrack.service.service.CourseService;
import com.airtribe.learntrack.service.service.EnrollmentService;
import com.airtribe.learntrack.service.service.StudentService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        char continueChoice = 0;
        StudentRepository studentRepository = new StudentRepository();
        StudentService studentService = new StudentServiceImpl(studentRepository);

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseServiceImpl(courseRepository);

        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(enrollmentRepository);

        do {
            try {

                System.out.println("LearnTrack [ Student & Course Management System]");
                System.out.println("1. Student Management");
                System.out.println("2. Course Management");
                System.out.println("3. Enrollment Management");
                System.out.println("4. Exit");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        int studentManagementChoice = 0;
                        do {
                            System.out.println("Student Management Menu");
                            System.out.println("1. Add new student");
                            System.out.println("2. View all students");
                            System.out.println("3. Search student by ID");
                            System.out.println("4. Deactivate a student");
                            System.out.println("5. Update student details");
                            System.out.println("6. Remove student");
                            System.out.println("7. Return to main menu");
                            System.out.println("Enter your choice: ");
                            studentManagementChoice = scanner.nextInt();
                            switch (studentManagementChoice) {
                                case 1:
                                    // Code to add new student
                                    System.out.println("Enter student first name: ");
                                    String firstName = scanner.nextLine();
                                    System.out.println("Enter student last name: ");
                                    String lastName = scanner.nextLine();
                                    System.out.println("Enter student email: ");
                                    String email = scanner.nextLine();
                                    System.out.println("Enter student batch: ");
                                    String batch = scanner.nextLine();
                                    System.out.println("Is the student active? (true/false): ");
                                    Boolean active = scanner.nextBoolean();
                                    System.out.println("Adding new student...");
                                    System.out.println(studentService.addStudent(
                                            new Student(firstName, lastName, email, batch, active)
                                    ).getDisplayName() + " added successfully!");
                                    break;

                                case 2:
                                    List<Student> students = studentService.viewAllStudents();
                                    if (students.isEmpty()) {
                                        System.out.println("No students found.");
                                    } else {
                                        System.out.println("Viewing all students...");
                                        studentService.viewAllStudents().forEach(student -> {
                                            displayStudent(student);
                                        });
                                    }
                                    break;

                                case 3:
                                    System.out.println("Enter Id to search student: ");
                                    Integer studentId = scanner.nextInt();
                                    try {
                                        Student searchedStudent = studentService.searchStudentById(studentId);
                                        System.out.println("Searching student by Id...");
                                        displayStudent(searchedStudent);
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;

                                case 4:
                                    System.out.println("Enter Id to deactivate student: ");
                                    studentId = scanner.nextInt();
                                    try {
                                        System.out.println("Deactivating a student...");
                                        Student student = studentService.deactivateStudent(studentId);
                                        System.out.println("Student with Id: " + studentId + " has been deactivated successfully!");
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;

                                case 5:
                                    System.out.println("Enter Id to update student: ");
                                    studentId = scanner.nextInt();
                                    try {
                                        Student studentToUpdate = studentService.searchStudentById(studentId);
                                        System.out.println("Which attribute do you want to update? (firstName, " +
                                                "lastName, email, batch, active(true/false)): ");
                                        String attribute = scanner.nextLine();
                                        attribute = attribute.toLowerCase(); // Convert input to lowercase for case-insensitive comparison

                                        switch (attribute) {
                                            case "firstName":
                                                System.out.println("Enter new first name: ");
                                                firstName = scanner.nextLine();
                                                studentToUpdate.setFirstName(firstName);
                                                break;
                                            case "lastName":
                                                System.out.println("Enter new last name: ");
                                                lastName = scanner.nextLine();
                                                studentToUpdate.setLastName(lastName);
                                                break;
                                            case "email":
                                                System.out.println("Enter new email: ");
                                                email = scanner.nextLine();
                                                studentToUpdate.setEmail(email);
                                                break;
                                            case "batch":
                                                System.out.println("Enter new batch: ");
                                                batch = scanner.nextLine();
                                                studentToUpdate.setBatch(batch);
                                                break;
                                            case "active":
                                                System.out.println("Enter new active status (true/false): ");
                                                active = scanner.nextBoolean();
                                                studentToUpdate.setActive(active);
                                                break;
                                            default:
                                                System.out.println("Invalid attribute. No changes made.");
                                                return;
                                        }

                                        Student updatedStudent = studentService.updateStudent(studentToUpdate);
                                        System.out.println("Student updated successfully!");
                                        displayStudent(updatedStudent);

                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;
                                case 6:
                                    System.out.println("Enter id to remove student: ");
                                    studentId = scanner.nextInt();
                                    try {
                                        Integer removedStudentId = studentService.removeStudent(studentId);
                                        System.out.println("Removing a student...");
                                        System.out.println("Student with Id: " + removedStudentId + " has been removed successfully!");
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;
                                case 7:
                                    System.out.println("Returning to LearnTrack [ Student & Course Management System]...");
                                    break;

                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }

                        } while (studentManagementChoice != 7);

                        break;
                    case 2:
                        int courseManagementChoice = 0;
                        do {
                            System.out.println("Course Management Menu");
                            System.out.println("1. Add new course");
                            System.out.println("2. View all courses");
                            System.out.println("3. Activate/Deactivate a course");
                            System.out.println("4. Return to main menu");
                            System.out.println("Enter your choice: ");
                            courseManagementChoice = scanner.nextInt();
                            switch (courseManagementChoice) {
                                case 1:
                                    // Code to add new course
                                    System.out.println("Enter course name: ");
                                    scanner.nextLine(); // Consume the leftover newline
                                    String courseName = scanner.nextLine();
                                    System.out.println("Enter course description: ");
                                    String courseDescription = scanner.nextLine();
                                    System.out.println("Enter course duration (in weeks): ");
                                    int courseDuration = scanner.nextInt();
                                    System.out.println("Is the course active? (true/false): ");
                                    boolean courseActive = scanner.nextBoolean();
                                    Course newCourse = new Course(courseName, courseDescription, courseDuration, courseActive);
                                    System.out.println("Adding new course...");
                                    System.out.println(courseService.addCourse(newCourse).getCourseName() +
                                            " added successfully!");
                                    break;
                                case 2:
                                    List<Course> courses = courseService.getAllCourses();
                                    if (courses.isEmpty()) {
                                        System.out.println("No courses found.");
                                    } else {
                                        System.out.println("Viewing all courses...");
                                        courseService.getAllCourses().forEach(course -> {
                                            displayCourse(course);
                                        });
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter Id to activate/deactivate course: ");
                                    Integer courseId = scanner.nextInt();
                                    try {
                                        Course course = courseService.getCourseById(courseId);
                                        System.out.println("Activate/Deactivate (1/0)?:");
                                        int activateDeactivateChoice = scanner.nextInt();
                                        if (activateDeactivateChoice == 1) {
                                            course.setActive(true);
                                            System.out.println("Activating Course...");
                                        } else if (activateDeactivateChoice == 0) {
                                            course.setActive(false);
                                            System.out.println("Deactivated course..");
                                        } else {
                                            System.out.println("Invalid choice. No changes made.");
                                            return;
                                        }

                                        course = courseService.updateCourse(course);
                                        System.out.println("Course with Id: " + courseId + " has been updated successfully!");
                                        displayCourse(course);
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;
                                case 4:
                                    System.out.println("Returning to LearnTrack [ Student & Course Management System]...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }

                        } while (courseManagementChoice != 4);

                        break;

                    case 3:
                        int enrollmentManagementChoice = 0;
                        do {
                            System.out.println("Enrollment Management Menu");
                            System.out.println("1. Enroll a student in a course");
                            System.out.println("2. View all enrollments for a student");
                            System.out.println("3. Mark enrollment as completed/cancelled");
                            System.out.println("4. Return to main menu");
                            System.out.println("Enter your choice: ");
                            enrollmentManagementChoice = scanner.nextInt();
                            switch (enrollmentManagementChoice) {
                                case 1:
                                    System.out.println("Enter student Id to enroll: ");
                                    Integer studentId = scanner.nextInt();
                                    studentService.searchStudentById(studentId); // Check if student exists, will throw exception if not found
                                    System.out.println("Enter course Id to enroll in: ");
                                    Integer courseId = scanner.nextInt();
                                    courseService.getCourseById(courseId); // Check if course exists, will throw exception if not found
                                    System.out.println("Enter enrollment date (DD-MM-YYYY): ");
                                    String inputDate = scanner.nextLine();
                                    Date enrollmentDate = null;
                                    try {
                                        enrollmentDate = Date.valueOf(
                                                LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                                        );
                                    } catch (DateTimeParseException e) {
                                        System.out.println("Invalid date format. Please enter the date in DD-MM-YYYY format.");
                                    }
                                    System.out.println("Enter enrollment status (enrolled/completed/cancelled): ");
                                    String enrollmentStatus = scanner.nextLine();
                                    System.out.println("Enrolling a student in a course...");
                                    enrollmentService.enrollStudentInCourse(
                                            new Enrollment(studentId, courseId, enrollmentDate, enrollmentStatus)
                                    );
                                    System.out.println("Enrollment added successfully!");
                                    break;
                                case 2:
                                    System.out.println("Enter student Id to view enrollments: ");
                                    studentId = scanner.nextInt();
                                    try {
                                        List<Enrollment> enrollments =
                                                enrollmentService.getEnrollmentsByStudentId(studentId);
                                        if (enrollments.isEmpty()) {
                                            System.out.println("No enrollments found for student ID: " + studentId);
                                        } else {
                                            System.out.println("Viewing all enrollments...");
                                            displayEnrollments(enrollments);
                                        }
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Marking enrollment as completed/cancelled...");
                                    System.out.println("Enter enrollment Id to update: ");
                                    Integer enrollmentId = scanner.nextInt();
                                    System.out.println("Enter new status (completed/cancelled): ");
                                    String newStatus = scanner.nextLine();
                                    try {
                                        Enrollment existingEnrollment = enrollmentService.getEnrollmentsByStudentId(enrollmentId)
                                                .stream()
                                                .filter(e -> e.getId().equals(enrollmentId))
                                                .findFirst()
                                                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with the given Id: " + enrollmentId));
                                        existingEnrollment.setStatus(newStatus);
                                        Enrollment updatedEnrollment =
                                                enrollmentService.updateEnrollmentStatus(existingEnrollment);
                                        displayEnrollments(List.of(updatedEnrollment));
                                    } catch (EntityNotFoundException entityNotFoundException) {
                                        System.out.println(entityNotFoundException.getMessage());
                                    }
                                    break;
                                case 4:
                                    System.out.println("Returning to LearnTrack [ Student & Course Management System]...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }

                        }
                        while (enrollmentManagementChoice != 4);
                        break;
                    case 4:
                        /*System.out.println("Do you really want to exit (Y/y) or (N/n)?");
                        continueChoice = scanner.nextLine().charAt(0);
                        if (continueChoice == 'Y' || continueChoice == 'y') {
                            System.out.println("Exiting LearnTrack [StudentServiceImpl & CourseServiceImpl Management System]... Goodbye!");
                            break;
                        } else if (continueChoice == 'N' || continueChoice == 'n') {
                            continue;
                        } else {
                            System.out.println("Invalid input! Exiting System...");
                            break;
                        }*/
                        System.out.println("Exiting LearnTrack [ Student & Course Management System]... Goodbye!");


                    default:
                        System.out.println("Invalid choice. Try again.");

                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println("InputMismatchException occurred, ExceptionType: " + inputMismatchException);
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
                System.out.println("Please enter valid input to continue...");
            } catch (Exception exception) {
                System.out.println("Exception occurred, ExceptionType: " + exception);
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
                System.out.println("Please enter valid input to continue...");
            }

        } while (choice != 4);


    }

    private static void displayEnrollments(List<Enrollment> enrollments) {
        enrollments.forEach(enrollment -> {
            System.out.println("Enrollment ID: " + enrollment.getId());
            System.out.println("Course ID: " + enrollment.getCourseId());
            System.out.println("Enrollment Date: " + enrollment.getEnrollmentDate());
            System.out.println("Status: " + enrollment.getStatus());
            System.out.println("-----------------------------");
        });
    }

    private static void displayCourse(Course course) {
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration (weeks): " + course.getDurationInWeeks());
        System.out.println("Active: " + course.getActive());
        System.out.println("-----------------------------");
    }

    private static void displayStudent(Student student) {
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Active: " + student.getActive());
        System.out.println("-----------------------------");
    }
}