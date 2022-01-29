package com.example.security.springsecurity.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.springsecurity.model.Student;
import com.example.security.springsecurity.userdetails.UserPermission;
import com.example.security.springsecurity.userdetails.UserRole;

@RestController
@RequestMapping("/v1/management/students")
public class StudentManagementController {

	private static List<Student> STUDENTS = 
			new ArrayList<>(
					Arrays.asList(
							new Student(1, "David"),
							new Student(2, "Venkat")
							)
					);
	private static Student DEFAULT_STUDENT = new Student(Integer.MAX_VALUE, "Default");
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		return STUDENTS.stream()
				.filter(student -> student.getId() == id)
				.findFirst()
				.orElse(DEFAULT_STUDENT);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	@GetMapping
	public List<Student> getAll(){
		return Optional.ofNullable(STUDENTS).isPresent() ? STUDENTS : List.of(DEFAULT_STUDENT);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/userroles")
	public Set<UserRole> getAllRoles(){
		return Set.of(UserRole.values());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/userpermissions")
	public Set<UserPermission> getAllPermissions(){
		return UserRole.STUDENT.getPermissions();
	}
	
	@PreAuthorize("hasAuthority('STUDENT:WRITE')")
	@PostMapping("{id}")
	public List<Student> addStudent(@PathVariable("id") int id, @RequestParam String name) {
		STUDENTS.add(new Student(id, name));
		return getAll();
	}
}
