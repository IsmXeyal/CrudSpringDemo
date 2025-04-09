package com.example.CrudSpringDemo.service;

import com.example.CrudSpringDemo.dto.*;

import java.util.List;

public interface CourseService {
    CourseResponseDto addCourse(CourseCreateDto dto);
    List<CourseResponseDto> getAllCourses();
    void updateCourse(Long id, CourseCreateDto updatedCourse);
    void deleteCourse(Long id);
}
