package com.pranav.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranav.assignment.modal.RevokeToken;
import com.pranav.assignment.repository.InMemoryRevokeTokenRepository;

@Service
public class RevokeTokenService {

	@Autowired
	InMemoryRevokeTokenRepository revokeTokenRepository;

	public String revokeToken(String token) {
		return revokeTokenRepository.save(token);
	}

	public boolean isTokenRevoked(String token) {
		return revokeTokenRepository.findByToken(token);
	}

}
