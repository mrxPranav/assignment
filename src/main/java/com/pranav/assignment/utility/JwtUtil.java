package com.pranav.assignment.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pranav.assignment.exceptionhandler.BlankCredentialsException;
import com.pranav.assignment.exceptionhandler.TokenExpiredException;
import com.pranav.assignment.service.RevokeTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	

	@Autowired
	RevokeTokenService revokeTokenService;

	private String SECRET_KEY = "Paf+AdV^uvFONDsEVfypW#7g9^k*Z8$V";

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	private Claims extractAllClaims(String token) {
		try {
			return Jwts
					.parser()
					.verifyWith(getSigningKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
		}catch (ExpiredJwtException e) {
			throw new TokenExpiredException("Token is expired! Please request for refresh token or login again.");
			}
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().claims(claims).subject(subject).header().empty().add("type", "JWT").and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // 5 minutes expiration time
				.signWith(getSigningKey()).compact();
	}

	public Boolean validateToken(String token) {
		return (!isTokenExpired(token) && !revokeTokenService.isTokenRevoked(token));
	}
}
