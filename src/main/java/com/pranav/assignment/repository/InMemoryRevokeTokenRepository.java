package com.pranav.assignment.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pranav.assignment.modal.RevokeToken;

@Repository
public class InMemoryRevokeTokenRepository {

	 private List<String> blacklist = new ArrayList<>();

	    public boolean findByToken(String token) {
	        return blacklist.contains(token);
	    }

	    public String save(String token) {
	    	if(!blacklist.contains(token))
	    		blacklist.add(token);
	        return token;
	    }

		public void delete(String token) {
			blacklist.remove(token);
		}
}
