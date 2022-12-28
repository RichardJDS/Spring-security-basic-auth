package com.security.students.api.controllers;

import com.security.students.api.entity.Student;
import com.security.students.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repository;


    @GetMapping
    public List<Student> findAllStudent(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
