package com.example.CrudSpringDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;

}
