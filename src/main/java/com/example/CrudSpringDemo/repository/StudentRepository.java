package com.example.CrudSpringDemo.repository;

import com.example.CrudSpringDemo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM Student WHERE surname = :surname AND joined_course > :date ORDER BY id DESC", nativeQuery = true)
    List<Student> findBySurnameAndJoinedCourseAfterOrderByIdDesc(@Param("surname") String surname, @Param("date") LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :name, s.surname = :surname, s.joinedCourse = :joinedCourse, s.course.Id = :courseId WHERE s.Id = :id")
    void updateStudent(@Param("id") Long id, @Param("name") String name, @Param("surname") String surname,
                       @Param("joinedCourse") LocalDate joinedCourse, @Param("courseId") Long courseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.Id = :id")
    void deleteStudent(@Param("id") Long id);
}
