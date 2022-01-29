package com.example.security.springsecurity.userdetails;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
	STUDENT(Set.of()),
	ADMIN(Set.of(
			UserPermission.values()			
			)),
	ADMINTRAINEE(Set.of(
			UserPermission.COURSE_READ, UserPermission.STUDENT_READ
			));
	
	private final Set<UserPermission> permissions;

	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<UserPermission> getPermissions(){
		return this.permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> authorities = permissions.stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return authorities;
	}
}
