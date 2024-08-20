package com.pranav.assignment.utility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pranav.assignment.exceptionhandler.BlankCredentialsException;
import com.pranav.assignment.exceptionhandler.TokenExpiredException;
import com.pranav.assignment.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	CustomUserDetailsService userService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		try {
			String authorizationHeader = request.getHeader("Authorization");
			String username = null;
			String jwt = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(jwt);
				if (username != null) {
					UserDetails userDetails = userService.loadUserByUsername(username);
					if (jwtUtil.validateToken(jwt)) {
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
								null, userDetails.getAuthorities());
						auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(auth);
					} else {
						throw new RuntimeException();
					}
				}
			} else {
				if (!path.contains("register") && !path.contains("login"))
					throw new NullPointerException();
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException ex) {
			filterChain.doFilter(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Token expired");
			response.getWriter().flush();
		} catch (MalformedJwtException ex) {
			filterChain.doFilter(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Token has been altered.");
			response.getWriter().flush();
		} catch (NullPointerException ex) {
			filterChain.doFilter(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Full authorization required.");
			response.getWriter().flush();
		} catch (TokenExpiredException ex) {
			filterChain.doFilter(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(ex.getMessage());
			response.getWriter().flush();
		} catch (Exception ex) {
			filterChain.doFilter(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Unauthorized or Invalid token.");
			response.getWriter().flush();
		}
	}
}
