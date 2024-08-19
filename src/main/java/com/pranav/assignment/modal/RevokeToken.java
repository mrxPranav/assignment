package com.pranav.assignment.modal;

public class RevokeToken {

	private String revokedToken;
	private User user;
	public RevokeToken(String revokedToken, User user) {
		super();
		this.revokedToken = revokedToken;
		this.user = user;
	}
	public RevokeToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRevokedToken() {
		return revokedToken;
	}
	public void setRevokedToken(String revokedToken) {
		this.revokedToken = revokedToken;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
