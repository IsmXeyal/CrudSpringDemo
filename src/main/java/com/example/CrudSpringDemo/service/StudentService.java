package com.example.CrudSpringDemo.service;

import com.example.CrudSpringDemo.dto.StudentCreateDto;
import com.example.CrudSpringDemo.dto.StudentResponseDto;
import com.example.CrudSpringDemo.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    StudentResponseDto addStudent(StudentCreateDto dto);
    List<StudentResponseDto> getAllStudents();
    List<StudentResponseDto> findBySurnameAndJoinedDateAfter(String surname, LocalDate date);
    void updateStudent(Long id, StudentCreateDto updatedStudent);
    void deleteStudent(Long id);
}
