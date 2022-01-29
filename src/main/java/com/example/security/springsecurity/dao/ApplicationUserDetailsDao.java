package com.example.security.springsecurity.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.security.springsecurity.model.auth.ApplicationUser;

public interface ApplicationUserDetailsDao {
	Optional<ApplicationUser> getByUserName(String userName);
}
