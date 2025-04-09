package com.example.CrudSpringDemo.dto;

import com.example.CrudSpringDemo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate joinedCourse;
    private String courseName;
}