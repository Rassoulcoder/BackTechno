package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.modele.User;
import com.example.demo.service.userService;

@Service
public class MysUserDetailsService implements UserDetailsService {
	@Autowired
	userService userservice;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userservice.findUserByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("Utilisateur introuvable");

		}
		List<GrantedAuthority> auths= new ArrayList<>();

		user.getRoles().forEach(role -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
			auths.add(authority);
		});
		//return new CustumDetail(user.getUserid(), user.getUsername(),user.getPassword(), auths);
		return new org.springframework.security.core.userdetails.User( user.getUsername(),user.getPassword(), auths);

	}

}
