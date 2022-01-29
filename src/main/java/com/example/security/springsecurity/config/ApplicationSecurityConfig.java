package com.example.security.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.springsecurity.service.ApplicationUserDetailsService;
import com.example.security.springsecurity.userdetails.UserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/v1/students").permitAll()
		.antMatchers("/v1/students/**").hasRole(UserRole.STUDENT.toString())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
//
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails Admin = User.builder()
//		.username("David")
//		.password(passwordEncoder().encode("pass123"))
////		.roles("ADMIN")  //internally ROLE_STUDENT
////		.roles(UserRole.ADMIN.toString())
//		.authorities(UserRole.ADMIN.getGrantedAuthorities())
//		.build();
//		
//		UserDetails Student = User.builder()
//				.username("Venkat")
//				.password(passwordEncoder().encode("pass345"))
////				.roles("STUDENT")  //internally ROLE_STUDENT
////				.roles(UserRole.STUDENT.toString())
//				.authorities(UserRole.STUDENT.getGrantedAuthorities())
//				.build();
//
//		UserDetails TraineeAdmin = User.builder()
//				.username("Koppula")
//				.password(passwordEncoder().encode("pass345"))
////				.roles("STUDENT")  //internally ROLE_STUDENT
////				.roles(UserRole.STUDENT.toString())
//				.authorities(UserRole.ADMINTRAINEE.getGrantedAuthorities())
//				.build();		
//		return new InMemoryUserDetailsManager(Admin, Student, TraineeAdmin);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserDetailsService);
		return provider;
	}
}
