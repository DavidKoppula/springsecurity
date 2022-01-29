package com.example.security.springsecurity.userdetails;

public enum UserPermission {
	STUDENT_READ,
	STUDENT_WRITE,
	COURSE_READ,
	COURSE_WRITE;
	
	public String getPermission() {
		return toString().replace("_", ":");
	}
	
//	@Override
//	public String toString() {
//		return this.name().replace("_", ":");
//	}
}
