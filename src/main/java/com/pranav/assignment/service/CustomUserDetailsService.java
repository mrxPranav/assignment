package com.pranav.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pranav.assignment.modal.User;
import com.pranav.assignment.repository.InMemoryUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	InMemoryUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
