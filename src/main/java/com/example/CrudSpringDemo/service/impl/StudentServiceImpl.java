package com.example.CrudSpringDemo.service.impl;

import com.example.CrudSpringDemo.dto.*;
import com.example.CrudSpringDemo.model.*;
import com.example.CrudSpringDemo.repository.*;
import com.example.CrudSpringDemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentResponseDto addStudent(StudentCreateDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setJoinedCourse(dto.getJoinedCourse());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);
        return toDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDto> findBySurnameAndJoinedDateAfter(String surname, LocalDate date) {
        List<Student> students = studentRepository.findBySurnameAndJoinedCourseAfterOrderByIdDesc(surname, date);
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudent(Long id, StudentCreateDto updatedStudent) {
        Course course = courseRepository.findById(updatedStudent.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        studentRepository.updateStudent(
                id,
                updatedStudent.getName(),
                updatedStudent.getSurname(),
                updatedStudent.getJoinedCourse(),
                course.getId()
        );
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }

    private StudentResponseDto toDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getJoinedCourse(),
                student.getCourse().getName()
        );
    }
}