package com.example.CrudSpringDemo.repository;

import com.example.CrudSpringDemo.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.name = :name, c.description = :description WHERE c.Id = :id")
    void updateCourse(Long id, String name, String description);
}
