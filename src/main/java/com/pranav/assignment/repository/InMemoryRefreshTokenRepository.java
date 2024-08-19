package com.pranav.assignment.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pranav.assignment.modal.RefreshToken;

@Repository
public class InMemoryRefreshTokenRepository {

	 private Map<String, RefreshToken> tokens = new HashMap<>();

	    public RefreshToken findByToken(String token) {
	        return tokens.get(token);
	    }

	    public RefreshToken save(RefreshToken token) {
	        tokens.put(token.getToken(), token);
	        return tokens.get(token.getToken());
	    }

		public void delete(String token) {
			 tokens.remove(token);
		}

		public void deleteByUsername(String username) {
			for (Map.Entry<String, RefreshToken> entry : tokens.entrySet()) {
				RefreshToken token = entry.getValue();
	            if (token.getUser().getUsername().equals(username)) {
	                tokens.remove(token.getToken());
	                break;
	            }
	        }
			
		}

}
