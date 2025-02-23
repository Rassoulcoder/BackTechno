package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.modele.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user = null;


		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (StreamReadException e) {

			e.printStackTrace();
		} catch (DatabindException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}   

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		org.springframework.security.core.userdetails.User spruser =

				(org.springframework.security.core.userdetails.User)	authResult.getPrincipal();
		List<String> roles = new ArrayList<>();
		spruser.getAuthorities().forEach(au ->{
			roles.add(au.getAuthority());
		});

		String jwt = JWT.create().
				withSubject(spruser.getUsername()).
				withArrayClaim("roles", roles.toArray(new String[roles.size()])).
				withExpiresAt(new Date(System.currentTimeMillis()+SecParams.DateExp)).sign(Algorithm.HMAC256(SecParams.Signature));


		response.addHeader("Authorization", jwt);
	}
	
	public Long getAuthenticatedUserId() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) principal;
	        return Long.valueOf(userDetails.getUsername()); // Suppose que l'ID est stocké comme username
	    } else {
	        throw new IllegalStateException("Utilisateur non authentifié");
	    }
	}








}
