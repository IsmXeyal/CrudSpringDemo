package com.example.CrudSpringDemo.service.impl;

import com.example.CrudSpringDemo.dto.*;
import com.example.CrudSpringDemo.model.Course;
import com.example.CrudSpringDemo.repository.CourseRepository;
import com.example.CrudSpringDemo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDto addCourse(CourseCreateDto dto) {
        logger.info("Adding new course: {}", dto.getName());

        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        Course savedCourse = courseRepository.save(course);
        logger.info("Course added with ID: {}", savedCourse.getId());

        return toDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        logger.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();
        logger.info("Found {} courses", courses.size());

        return courses.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void updateCourse(Long id, CourseCreateDto updatedCourse) {
        logger.info("Updating course with ID: {}", id);

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Course with ID {} not found", id);
                    return new RuntimeException("Course not found");
                });

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());

        courseRepository.save(existingCourse);
        logger.info("Course with ID {} updated successfully", id);
    }

    @Override
    public void deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);
        courseRepository.deleteById(id);
        logger.info("Course with ID {} deleted", id);
    }

    private CourseResponseDto toDto(Course course) {
        return new CourseResponseDto(
                course.getId(),
                course.getName(),
                course.getDescription()
        );
    }
}