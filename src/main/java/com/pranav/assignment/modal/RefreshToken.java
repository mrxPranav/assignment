package com.pranav.assignment.modal;

import java.time.Instant;

public class RefreshToken {

	private String refreshToken;
	private Instant expiryDate;
	private User user;
	public RefreshToken() {
		super();
	}
	public RefreshToken(String token, Instant expiryDate, User user) {
		super();
		this.refreshToken = token;
		this.expiryDate = expiryDate;
		this.user = user;
	}
	public String getToken() {
		return refreshToken;
	}
	public void setToken(String token) {
		this.refreshToken = token;
	}
	public Instant getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
