package com.example.security.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.springsecurity.dao.ApplicationUserDetailsDao;

@Service
public class ApplicationUserDetailsService implements UserDetailsService{

	@Autowired
	private ApplicationUserDetailsDao applicationUserDetailsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDetailsDao.getByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
	}

}
