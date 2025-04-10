package com.example.CrudSpringDemo.service.impl;

import com.example.CrudSpringDemo.dto.*;
import com.example.CrudSpringDemo.model.*;
import com.example.CrudSpringDemo.repository.*;
import com.example.CrudSpringDemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentResponseDto addStudent(StudentCreateDto dto) {
        logger.info("Attempting to add new student: {} {}", dto.getName(), dto.getSurname());
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> {
                    logger.error("Course with ID {} not found", dto.getCourseId());
                    return new RuntimeException("Course not found");
                });

        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setEmail(dto.getEmail());
        student.setJoinedCourse(LocalDate.now());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);
        logger.info("Student added with ID: {}", savedStudent.getId());
        return toDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        logger.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        logger.info("Found {} students", students.size());

        return students.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<StudentResponseDto> findBySurnameAndJoinedDateAfter(String surname, LocalDate date) {
        logger.info("Searching students with surname '{}' who joined after {}", surname, date);
        List<Student> students = studentRepository.findBySurnameAndJoinedCourseAfterOrderByIdDesc(surname, date);
        logger.info("Total number of students found: {}", students.size());

        return students.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void updateStudent(Long id, StudentCreateDto updatedStudent) {
        logger.info("Updating student with ID: {}", id);

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student with ID {} not found", id);
                    return new RuntimeException("Student not found");
                });

        Course newCourse = courseRepository.findById(updatedStudent.getCourseId())
                .orElseThrow(() -> {
                    logger.error("Course with this ID {} not found", updatedStudent.getCourseId());
                    return new RuntimeException("Course not found");
                });

        LocalDate joinedCourseDate = existingStudent.getCourse().getId().equals(newCourse.getId())
                ? updatedStudent.getJoinedCourse()
                : LocalDate.now();

        studentRepository.updateStudent(
                id,
                updatedStudent.getName(),
                updatedStudent.getSurname(),
                updatedStudent.getEmail(),
                joinedCourseDate,
                newCourse.getId()
        );
        logger.info("Student with ID {} updated successfully", id);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);
        studentRepository.deleteStudent(id);
        logger.info("Student with ID {} deleted", id);
    }

    private StudentResponseDto toDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getJoinedCourse(),
                student.getCourse().getName()
        );
    }
}