package com.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.utils.JWTUtils;

@Component
public class JWTAuthFilter extends OncePerRequestFilter{
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private CachingUserDetailsService cachingUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		
		final String JWTToken;
		final String UserEmail;
		
		if(authHeader == null || authHeader.isBlank())
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		JWTToken = authHeader.substring(7);
		UserEmail=jwtUtils.getUserNameFromJwtToken(JWTToken);
		
		if(UserEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = cachingUserDetailsService.loadUserByUsername(UserEmail);
			
			if(jwtUtils.validateToken(JWTToken)) {
				
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
			}
			filterChain.doFilter(request, response);
		}
		
		
	}

}
