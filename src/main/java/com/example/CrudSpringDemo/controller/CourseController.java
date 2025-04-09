package com.example.CrudSpringDemo.controller;

import com.example.CrudSpringDemo.dto.*;
import com.example.CrudSpringDemo.service.CourseService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public CourseResponseDto addCourse(@RequestBody CourseCreateDto courseCreateDto) {
        return courseService.addCourse(courseCreateDto);
    }

    @GetMapping("/getAll")
    public List<CourseResponseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PutMapping("/update/{id}")
    public void updateCourse(@PathVariable Long id, @RequestBody CourseCreateDto courseCreateDto) {
        courseService.updateCourse(id, courseCreateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}