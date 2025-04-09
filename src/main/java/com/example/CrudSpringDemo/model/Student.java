package com.example.CrudSpringDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String surname;
    private String email;
    private LocalDate joinedCourse;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
