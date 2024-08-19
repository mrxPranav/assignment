package com.pranav.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenRequest {

	@JsonProperty("refreshToken")
	private String refreshToken;

	
	public RefreshTokenRequest() {
	}

	public RefreshTokenRequest(String token) {
		this.refreshToken = token;
	}

	public String getToken() {
		return refreshToken;
	}

	public void setToken(String token) {
		this.refreshToken = token;
	}
	
	
}
