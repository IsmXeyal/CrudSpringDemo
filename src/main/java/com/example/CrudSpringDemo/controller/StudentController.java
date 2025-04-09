package com.example.CrudSpringDemo.controller;

import com.example.CrudSpringDemo.dto.*;
import com.example.CrudSpringDemo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/add")
    public StudentResponseDto addStudent(@RequestBody StudentCreateDto studentCreateDto) {
        return studentService.addStudent(studentCreateDto);
    }

    @GetMapping("/getAll")
    public List<StudentResponseDto> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/search")
    public List<StudentResponseDto> findBySurnameAndJoinedDateAfter(@RequestParam String surname, @RequestParam String date) {
        return studentService.findBySurnameAndJoinedDateAfter(surname, LocalDate.parse(date));
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentCreateDto studentCreateDto) {
        studentService.updateStudent(id, studentCreateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
