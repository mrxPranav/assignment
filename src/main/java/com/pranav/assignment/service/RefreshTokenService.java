package com.pranav.assignment.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranav.assignment.modal.RefreshToken;
import com.pranav.assignment.repository.InMemoryRefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Autowired
	InMemoryRefreshTokenRepository refreshTokenRepository;

	@Autowired
	CustomUserDetailsService userDetailsService;

	public RefreshToken create(String username) {
		RefreshToken rt = new RefreshToken(UUID.randomUUID().toString(), Instant.now().plusMillis(1000 * 60 * 20), // 20 minute
				userDetailsService.getUserByUsername(username));
		return refreshTokenRepository.save(rt);
	}

	public RefreshToken findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token.getToken());
			throw new RuntimeException(
					token.getToken() + " Refresh token was expired! Please make a new login request.");
			
		}
		return token;
	}

	public void deleteByUsername(String username) {
		refreshTokenRepository.deleteByUsername(username);
		
	}
}
