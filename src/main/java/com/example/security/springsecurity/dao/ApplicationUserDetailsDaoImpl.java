package com.example.security.springsecurity.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.security.springsecurity.model.auth.ApplicationUser;
import com.example.security.springsecurity.repository.ApplicationUserRepository;
import com.example.security.springsecurity.userdetails.UserRole;


@Repository
public class ApplicationUserDetailsDaoImpl implements ApplicationUserDetailsDao{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Override
	public Optional<ApplicationUser> getByUserName(String userName) {
		return getAllByUserName()
				.stream()
				.filter(user -> user.getUsername().equals(userName))
				.findFirst();
	}

	@PostConstruct
	private void loadApplicationUsers() {
		userRepository.saveAll(Arrays.asList(user1(), user2(), user3()));
	}

	private List<ApplicationUser> getAllByUserName(){
		return userRepository.findAll();
	}
	private ApplicationUser user1() {
		return new ApplicationUser(
				UserRole.ADMIN.getGrantedAuthorities(),
				"David",
				passwordEncoder.encode("pass123"),
				true,
				true,
				true,
				true
		);
	}
	private ApplicationUser user2() {
		return new ApplicationUser(
				UserRole.STUDENT.getGrantedAuthorities(),
				"Venkat",
				passwordEncoder.encode("pass345"),
				true,
				true,
				true,
				true
		);
	}
	private ApplicationUser user3() {
		return new ApplicationUser(
				UserRole.ADMINTRAINEE.getGrantedAuthorities(),
				"Koppula",
				passwordEncoder.encode("pass345"),
				true,
				true,
				true,
				true
		);
	}

}
