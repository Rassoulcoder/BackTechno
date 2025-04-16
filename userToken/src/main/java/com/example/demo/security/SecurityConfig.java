package com.example.demo.security;


import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	AuthenticationManager authmgr;

	@Bean
	SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		http.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.csrf(csrf ->csrf.disable())
		.cors(cors -> cors.configurationSource(new CorsConfigurationSource()
		{
			@Override
		    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cors = new CorsConfiguration();
                cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                cors.setAllowedMethods(Collections.singletonList("*"));
                cors.setAllowCredentials(true);
                cors.setAllowedHeaders(Collections.singletonList("*"));
                cors.setExposedHeaders(Collections.singletonList("Authorization"));
                cors.setMaxAge(3600L);
                return cors;
			}
		}))
		.authorizeHttpRequests(requests -> requests
				.requestMatchers(HttpMethod.POST, "/api/v1/user","/login", "/api/v1/postule", "/api/v1/anonnce","/error", "/api/v1/user/registerAdmin").permitAll()
				.requestMatchers("/ap1/v1/document/envoyer","/ap1/v1/document/docs","/ap1/v1/document/files/{filename:.+}","/api/v1/user/verifyEmail/**").permitAll()
			    .requestMatchers(HttpMethod.GET, "/login", "/error","/api/v1/postule/user","/api/v1/user/candi","/api/v1/anonnce").permitAll()
			    .requestMatchers("/all").hasAuthority("ADMIN")
			    .anyRequest().authenticated()).addFilterBefore(new JWTAuthenticationFilter(authmgr), 
						UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); 
		return http.build();

	}
	



}
