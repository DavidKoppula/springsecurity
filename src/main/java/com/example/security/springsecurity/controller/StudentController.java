package com.example.security.springsecurity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.springsecurity.model.Student;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

	private static final List<Student> STUDENTS = 
			List.of(
			new Student(1, "David"),
			new Student(2, "Venkat")
			);
	private static Student DEFAULT_STUDENT = new Student(Integer.MAX_VALUE, "Default");
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		return STUDENTS.stream()
				.filter(student -> student.getId() == id)
				.findFirst()
				.orElse(DEFAULT_STUDENT);
	}
	
	@GetMapping
	public List<Student> getAll(){
		return Optional.ofNullable(STUDENTS).isPresent() ? STUDENTS : List.of(DEFAULT_STUDENT);
	}
}
