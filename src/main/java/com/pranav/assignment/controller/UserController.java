package com.pranav.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.assignment.dto.JwtResponse;
import com.pranav.assignment.dto.RefreshTokenRequest;
import com.pranav.assignment.exceptionhandler.BlankCredentialsException;
import com.pranav.assignment.modal.RefreshToken;
import com.pranav.assignment.modal.User;
import com.pranav.assignment.service.CustomUserDetailsService;
import com.pranav.assignment.service.RefreshTokenService;
import com.pranav.assignment.service.RevokeTokenService;
import com.pranav.assignment.utility.JwtUtil;

@RestController
public class UserController {

	@Autowired
	CustomUserDetailsService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	RevokeTokenService revokeTokenService;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		if (!user.getUsername().isBlank() && !user.getPassword().isBlank())
			return userService.createUser(user);
		else
			throw new BlankCredentialsException("Username or password is empty.");
	}

	@PostMapping("/login")
	public JwtResponse login(@RequestBody User user) {
		if (user.getUsername().isBlank() || user.getPassword().isBlank())
			throw new BlankCredentialsException("Username or password is empty.");
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
			String jwt = jwtUtil.generateToken(userDetails.getUsername());
			RefreshToken refreshToken = refreshTokenService.create(userDetails.getUsername());
			return new JwtResponse(jwt, refreshToken.getToken());
		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
	}

	@PostMapping("/refreshtoken")
	public JwtResponse refreshToken(@RequestHeader("Authorization") String token,
			@RequestBody RefreshTokenRequest request) {
		try {
			// validating refresh token
			RefreshToken refreshToken = refreshTokenService.findByToken(request.getToken());
			User user = (refreshTokenService.verifyExpiration(refreshToken)).getUser();

			// generating new token
			String jwt = jwtUtil.generateToken(user.getUsername());

			String tokenValue = token.replace("Bearer ", "");
			// adding current token to blacklist
			revokeTokenService.revokeToken(tokenValue);
			return new JwtResponse(jwt, refreshToken.getToken());
		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid Refresh token.");
		}
	}

	@PostMapping("/revoketoken")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
		String tokenValue = token.replace("Bearer ", "");
		// adding token to blacklist
		revokeTokenService.revokeToken(tokenValue);
		// deleting refresh token for revoked access-token
		refreshTokenService.deleteByUsername(jwtUtil.extractUsername(tokenValue));
		return new ResponseEntity<>("Token revoked successfully", HttpStatus.OK);
	}

}
