package com.example.security.springsecurity.model.auth;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class ApplicationUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6700149692565216559L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Transient
	private Set<? extends GrantedAuthority> authorities;
	private String authoritiesForDb;
	private String password;
	private String userName;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;

	public ApplicationUser() {
		
	}
	
	public ApplicationUser(Set<? extends GrantedAuthority> authorities, String userName, String password,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		this.authorities = authorities;
		this.password = password;
		this.userName = userName;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Column(name = "authorities")
	@Access(AccessType.PROPERTY)
	public String getAuthoritiesForDb() {
		return String.join(", ", 
				this.authorities.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.toList()));
	}
	
	private void setAuthoritiesForDb(String authoritiesForDb) {
		this.authoritiesForDb = authoritiesForDb;
	}
}
