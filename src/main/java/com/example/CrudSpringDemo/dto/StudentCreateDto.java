package com.example.CrudSpringDemo.dto;

import com.example.CrudSpringDemo.model.Course;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCreateDto {
    private String name;
    private String surname;
    private LocalDate joinedCourse;
    private Long courseId;
}
