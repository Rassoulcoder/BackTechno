package com.example.demo.service;

import java.util.List
;

import com.example.demo.modele.Role;
import com.example.demo.modele.User;
import com.example.demo.service.register.RegistrationRequest;

public interface userService {
	User saveUser(User user);
	User findUserByUsername (String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
	List<User> findAllUsers();
	User registerUser(RegistrationRequest request);
	
	
}
