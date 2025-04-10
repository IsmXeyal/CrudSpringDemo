package com.example.CrudSpringDemo.service.impl;

import com.example.CrudSpringDemo.dto.CourseCreateDto;
import com.example.CrudSpringDemo.dto.CourseResponseDto;
import com.example.CrudSpringDemo.model.Course;
import com.example.CrudSpringDemo.repository.CourseRepository;
import com.example.CrudSpringDemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDto addCourse(CourseCreateDto dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        Course savedCourse = courseRepository.save(course);
        return toDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void updateCourse(Long id, CourseCreateDto updatedCourse) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());

        courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponseDto toDto(Course course) {
        return new CourseResponseDto(
                course.getId(),
                course.getName(),
                course.getDescription()
        );
    }
}