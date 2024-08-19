package com.pranav.assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pranav.assignment.exceptionhandler.CustomAuthenticationEntryPoint;
import com.pranav.assignment.service.CustomUserDetailsService;
import com.pranav.assignment.utility.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailsService userService;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	CustomAuthenticationEntryPoint entryPoint;

	public SecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userService = userDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		try {
			http.authorizeHttpRequests(
					auth -> auth.requestMatchers("/register","/login").permitAll().anyRequest().authenticated());
			http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			http.csrf(csrf -> csrf.disable());
			http.exceptionHandling(handling -> handling.authenticationEntryPoint(entryPoint));
			return http.build();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
